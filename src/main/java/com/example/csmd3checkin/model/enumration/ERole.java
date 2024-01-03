package com.example.csmd3checkin.model.enumration;

public enum ERole {
    ADMIN(1, "admin"), EMPLOYEE(2, "employee");
    private int id;
    private String name;
    ERole() {
    }
    ERole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ERole findByName(String name){
        for (ERole e : values()){
            if(e.getName().equalsIgnoreCase(name)){
                return e;
            }
        }
        return null;
    }
}
