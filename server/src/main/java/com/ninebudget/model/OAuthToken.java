package com.ninebudget.model;

import java.io.Serializable;

public class OAuthToken implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;
    private User user;

    public OAuthToken() {
    }

    public OAuthToken(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
