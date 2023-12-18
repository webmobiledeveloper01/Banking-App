package com.ifstatic.banking.Models;

public class UserModel {

    private int id;

    private int profile;

    private String name;

    private String email;

    public UserModel(int id, int profile, String name) {
        this.id = id;
        this.profile = profile;
        this.name = name;
    }

    public UserModel(int id, int profile, String name, String email) {
        this.id = id;
        this.profile = profile;
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
