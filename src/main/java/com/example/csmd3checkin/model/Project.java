package com.example.csmd3checkin.model;

import java.time.LocalDate;

public class Project {
    private int id;
    private String name;
    private LocalDate deadLine;
    private boolean status;
    private int teamId;
    private Team team;

    public Project() {
    }

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(int id, String name, LocalDate deadLine, boolean status) {
        this.id = id;
        this.name = name;
        this.deadLine = deadLine;
        this.status = status;
    }

    public Project(String name, int teamId) {
        this.name = name;
        this.teamId = teamId;
    }

    public Project(int id, String name, int teamId) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
    }

    public Project(String name, LocalDate deadline, int teamId) {
        this.name = name;
        this.deadLine = deadline;
        this.teamId = teamId;
    }

    public Project(int id, String name, LocalDate deadline, boolean status, Team team) {
        this.id = id;
        this.name = name;
        this.deadLine = deadline;
        this.status = status;
        this.team = team;
        this.teamId = team.getId();
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
