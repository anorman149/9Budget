package com.ninebudget.model;

import io.jsonwebtoken.Claims;

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
    public void extractProperties() {
        //Extract Custom Props
    }

    @Override
    public void verify() throws Exception {
        //Verify Signature
    }
}
