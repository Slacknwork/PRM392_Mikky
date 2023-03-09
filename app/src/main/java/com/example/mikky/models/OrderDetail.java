package com.example.mikky.models;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private int orderId;
    private int drinkId;
    private int quantity;
    private float price;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int drinkId, int quantity, float price) {
        this.orderId = orderId;
        this.drinkId = drinkId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
