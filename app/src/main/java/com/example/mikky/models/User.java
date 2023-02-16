package com.example.mikky.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.xml.transform.Result;

public class User implements Serializable {

    private int userId;

    private String username;

    private String password;

    private int phonenumber;

    private String address;

    private int role;

    public User() {
    }

    public User(int userId, String username, String password, int phonenumber, String address, int role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.address = address;
        this.role = role;
    }

// Getter Methods

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public int getRole() {
        return role;
    }

    // Setter Methods

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
