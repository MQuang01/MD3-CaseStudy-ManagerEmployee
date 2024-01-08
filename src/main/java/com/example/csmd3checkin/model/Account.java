package com.example.csmd3checkin.model;

import com.example.csmd3checkin.model.enumration.ERole;

public class Account {
    private int id;
    private String username;
    private String password;
    private ERole role;

    public Account() {
    }

    public Account(int id, String username, String password, ERole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account(String username, String password, ERole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account(String username, String psw) {
        this.username = username;
        this.password = psw;
    }

    public Account(ERole role) {
        this.role = role;
    }


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

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
}
