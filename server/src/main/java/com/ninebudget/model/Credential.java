package com.ninebudget.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Credential implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String custom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }
}
