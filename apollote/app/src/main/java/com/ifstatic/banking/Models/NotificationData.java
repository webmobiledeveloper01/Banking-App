package com.ifstatic.banking.Models;

public class NotificationData {

    private String title;

    private String id;

    private int profile;

    private String time;

    private boolean isPayRequest;

    public NotificationData(String title, String id, int profile, String time, boolean isPayRequest) {
        this.title = title;
        this.id = id;
        this.profile = profile;
        this.time = time;
        this.isPayRequest = isPayRequest;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isPayRequest() {
        return isPayRequest;
    }

    public void setPayRequest(boolean payRequest) {
        isPayRequest = payRequest;
    }
}
