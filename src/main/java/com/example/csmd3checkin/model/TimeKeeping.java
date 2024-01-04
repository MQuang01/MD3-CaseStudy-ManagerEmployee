package com.example.csmd3checkin.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeKeeping {
    private int id;
    private LocalDateTime day;
    private LocalTime timeCheckin;
    private LocalTime timeCheckout;
    private boolean status;
    private int memberId;
    private Member member;

    public TimeKeeping() {
    }

    public TimeKeeping(int id, LocalDateTime day, LocalTime timeCheckin, LocalTime timeCheckout, boolean status, int memberId) {
        this.id = id;
        this.day = day;
        this.timeCheckin = timeCheckin;
        this.timeCheckout = timeCheckout;
        this.status = status;
        this.memberId = memberId;
    }
    public TimeKeeping(int id, LocalDateTime day, boolean status, Member member) {
        this.id = id;
        this.day = day;
        this.status = status;
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDay() {
        return day;
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }

    public LocalTime getTimeCheckin() {
        return timeCheckin;
    }

    public void setTimeCheckin(LocalTime timeCheckin) {
        this.timeCheckin = timeCheckin;
    }

    public LocalTime getTimeCheckout() {
        return timeCheckout;
    }

    public void setTimeCheckout(LocalTime timeCheckout) {
        this.timeCheckout = timeCheckout;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "TimeKeeping{" +
                "id=" + id +
                ", day=" + day +
                ", timeCheckin=" + timeCheckin +
                ", timeCheckout=" + timeCheckout +
                ", status=" + status +
                ", memberId=" + memberId +
                '}';
    }
}
