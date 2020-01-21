package com.ninebudget.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Date;

@Component
@PropertySource("classpath:store.properties")
public class JWTToken implements Token<String> {
    private static final long AUTH_TOKEN_EXPIRE_TIME = 5 * 60 * 18 * 1000; //90 Minutes

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

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void verify(PublicKey publicKey) throws Exception {
        this.claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
    }

    @Override
    public String provide(OAuthToken oAuthToken) throws Exception {
        //Load Key
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(keyFileLocation), keyPass.toCharArray());
        Key key = ks.getKey(keyAlias, keyPass.toCharArray());

        //Create JWT Token
        return Jwts.builder()
                .setSubject("davis") //TODO correct - oAuthToken.getUser().getCredential().getUsername()
                .setAudience("9budget")
                .setExpiration(new Date(System.currentTimeMillis() + AUTH_TOKEN_EXPIRE_TIME))
                .setIssuedAt(new Date())
                .setIssuer("9budget")
                .signWith(SignatureAlgorithm.RS256, key)
                .compact();
    }

    @Override
    public void authenticate() throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(keyFileLocation), keyPass.toCharArray());
        Certificate cert = ks.getCertificate(keyAlias);
        PublicKey publicKey = cert.getPublicKey();

        //Decode and grab all information from the Token
        verify(publicKey);
    }
}
