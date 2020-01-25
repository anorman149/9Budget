package com.ninebudget.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

@Component
@PropertySource("classpath:store.properties")
public class JWTToken implements Token<String> {
    private Claims claims;
    private String token;

    @Value("#{'${keyPass}'}")
    private String keyPass;

    @Value("#{'${keyAlias}'}")
    private String keyAlias;

    @Value("#{'${keyFileLocation}'}")
    private String keyFileLocation;

    public JWTToken() {
    }

    public JWTToken(String token) {
        this.token = token;
    }

    public Date getIssuedAt() {
        return claims.getIssuedAt();
    }

    public String getSubject() {
        return claims.getSubject();
    }

    public String getIssuer() {
        return claims.getIssuer();
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void verify(PublicKey publicKey) {
        this.claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
    }

    @Override
    public String provide(OAuthToken oAuthToken) throws TokenException {
        //Load Key
        Key key;
        try {
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(new FileInputStream(keyFileLocation), keyPass.toCharArray());
            key = ks.getKey(keyAlias, keyPass.toCharArray());
        } catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new TokenException("Error Loading Keystore");
        }

        //Create JWT Token
        return Jwts.builder()
                .setSubject("davis") //TODO correct - oAuthToken.getUser().getCredential().getUsername()
                .setAudience("9budget")
                .setExpiration(Date.from(Instant.now().plusSeconds(Token.AUTH_TOKEN_EXPIRE_TIME)))
                .setIssuedAt(new Date())
                .setIssuer("9budget")
                .signWith(SignatureAlgorithm.RS256, key)
                .compact();
    }

    @Override
    public void authenticate() throws TokenException {
        PublicKey publicKey;
        try {
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(new FileInputStream(keyFileLocation), keyPass.toCharArray());
            Certificate cert = ks.getCertificate(keyAlias);
            publicKey = cert.getPublicKey();
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new TokenException("Error Loading Keystore");
        }

        //Decode and grab all information from the Token
        verify(publicKey);
    }
}
