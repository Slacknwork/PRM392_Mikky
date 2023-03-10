package com.example.mikky.models;

import java.io.Serializable;

public class Order implements Serializable {
    private int orderId;
    private int userId;
    private String date;
    private String status;
    private float totalPrice;

    public Order() {
    }

    public Order(int orderId, int userId, String date, String status, float totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
