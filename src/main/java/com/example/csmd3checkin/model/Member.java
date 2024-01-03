package com.example.csmd3checkin.model;

import java.time.LocalDate;

public class Member {
    private int id;
    private String fullName;
    private String phoneNum;
    private LocalDate doB;
    private String email;
    private int teamId;
    private int accountId;

    public Member() {
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", doB=" + doB +
                ", email='" + email + '\'' +
                ", teamId=" + teamId +
                ", accountId=" + accountId +
                '}';
    }
}
