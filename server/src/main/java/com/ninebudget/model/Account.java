package com.ninebudget.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private List<User> users;
    private User primaryUser;

    @NotNull
    private boolean active;

    public Account() {
    }

    public Account(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(User primaryUser) {
        this.primaryUser = primaryUser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
