package com.example.csmd3checkin.model;

public class Team {
    private int id;
    private String name;
    private int projectId;



    public Team() {
    }

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team(int id, String name, int projectId) {
        this.id = id;
        this.name = name;
        this.projectId = projectId;
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
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
