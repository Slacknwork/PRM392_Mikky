package com.example.mikky.models;

import java.io.Serializable;

public class Drink implements Serializable {
    private int drinkId;
    private String drinkname;
    private int drinkCateId;
    private String drinkImage;
    private String description;
    private double price;

    public Drink(){
    }

    public Drink(int drinkId, String drinkname, int drinkCateId, String drinkImage, String description, double price) {
        this.drinkId = drinkId;
        this.drinkname = drinkname;
        this.drinkCateId = drinkCateId;
        this.drinkImage = drinkImage;
        this.description = description;
        this.price = price;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkname() {
        return drinkname;
    }

    public void setDrinkname(String drinkname) {
        this.drinkname = drinkname;
    }

    public int getDrinkCateId() {
        return drinkCateId;
    }

    public void setDrinkCateId(int drinkCateId) {
        this.drinkCateId = drinkCateId;
    }

    public String getDrinkImage() {
        return drinkImage;
    }

    public void setDrinkImage(String drinkImage) {
        this.drinkImage = drinkImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
