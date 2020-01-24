package com.ninebudget.model;

import com.ninebudget.model.dto.UserDto;

import java.io.Serializable;

public class OAuthToken implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;
    private UserDto user;

    public OAuthToken() {
    }

    public OAuthToken(UserDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}