package com.example.csmd3checkin.model;

import java.time.LocalDate;
import java.util.List;

public class Member {
    private int id;
    private String fullName;
    private String phoneNum;
    private LocalDate doB;
    private String email;
    private int teamId;
    private Team team;
    private int accountId;
    private Account account;
    private List<Project> projectList;
    public Member() {
    }

    public Member(int id, String fullName, String phoneNum, LocalDate doB, String email, int teamId, Team team, Account account, List<Project> projects) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.doB = doB;
        this.email = email;
        this.teamId = teamId;
        this.team = team;
        this.account = account;
        this.projectList = projects;
    }

    public Member(String fullName, String phoneNum, LocalDate doB, String email, Team team, Account account) {
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.doB = doB;
        this.email = email;
        this.team = team;
        this.account = account;
    }

    public Member(int id, String fullName, String phoneNum, LocalDate doB, String email, int teamId, int accountId) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.doB = doB;
        this.email = email;
        this.teamId = teamId;
        this.accountId = accountId;
    }

    public Member(String fullName, String phoneNum, LocalDate doB, String email, int teamId, int accountId) {
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.doB = doB;
        this.email = email;
        this.teamId = teamId;
        this.accountId = accountId;
    }

    public Member(int id, String fullName, String phoneNum, LocalDate doB, String email, int teamId, Account account) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.doB = doB;
        this.email = email;
        this.teamId = teamId;
        this.account = account;
        this.accountId = account.getId();
    }

    public Member(int id, String fullName, String phoneNum, LocalDate doB, String email, Team team) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.doB = doB;
        this.email = email;
        this.team = team;
        this.teamId = team.getId();
    }

    public Member(int id, String name, String email, Team team) {
        this.id = id;
        this.fullName = name;
        this.email = email;
        this.team = team;
        this.teamId = team.getId();
    }

    public Member(String fullName, String phoneNum, LocalDate doB, String email, Team team, Account account, List<Project> projects) {
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.doB = doB;
        this.email = email;
        this.team = team;
        this.account = account;
        this.projectList = projects;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProject(List<Project> projects) {
        this.projectList = projects;
    }

    public Member(int memberId, String name) {
        this.id = memberId;
        this.fullName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public LocalDate getDoB() {
        return doB;
    }

    public void setDoB(LocalDate doB) {
        this.doB = doB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
