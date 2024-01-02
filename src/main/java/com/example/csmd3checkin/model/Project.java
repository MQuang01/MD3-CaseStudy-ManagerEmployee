package com.example.csmd3checkin.model;

import java.time.LocalDate;

public class Project {
    private int id;
    private String name;
    private LocalDate deadLine;
    private boolean status;
    private int teamId;

    public Project() {
    }

    public Project(int id, String name, LocalDate deadLine, boolean status, int teamId) {
        this.id = id;
        this.name = name;
        this.deadLine = deadLine;
        this.status = status;
        this.teamId = teamId;
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

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
