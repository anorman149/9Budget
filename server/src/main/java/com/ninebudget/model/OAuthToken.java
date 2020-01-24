package com.ninebudget.model;

import com.ninebudget.model.dto.ApplicationUserDto;

import java.io.Serializable;

public class OAuthToken implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;
    private ApplicationUserDto user;

    public OAuthToken() {
    }

    public OAuthToken(ApplicationUserDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ApplicationUserDto getUser() {
        return user;
    }

    public void setUser(ApplicationUserDto user) {
        this.user = user;
    }
}