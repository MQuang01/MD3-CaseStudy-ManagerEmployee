package com.example.csmd3checkin.model.enumration;

public enum ERole {
    ADMIN(1, "admin"), EMPLOYEES(2, "employee");
    private int id;
    private String name;
    ERole() {
    }
    ERole(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
