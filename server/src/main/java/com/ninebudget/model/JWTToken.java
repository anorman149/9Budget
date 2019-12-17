package com.ninebudget.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.PublicKey;
import java.util.Date;

public class JWTToken implements Token<String> {
    private Claims claims;
    private String token;

    //Needed for Spring
    public JWTToken() {
    }

    public JWTToken(String token) {
        this.token = token;
    }

    public Date getIssuedAt(){
        return claims.getIssuedAt();
    }

    public String getSubject(){
        return claims.getSubject();
    }

    public String getIssuer(){
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
}
