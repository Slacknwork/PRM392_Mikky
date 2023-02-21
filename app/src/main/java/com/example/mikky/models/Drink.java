package com.example.mikky.models;

import java.io.Serializable;

public class Drink implements Serializable {
    private int id;
    private String drinkname;
    private int cateId;
    private String image;
    private String description;
    private double price;

    public Drink(){
    }

    public Drink(int id, String name, int cateId, String image, String description, double price) {
        this.id = id;
        this.drinkname = name;
        this.cateId = cateId;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrinkname() {
        return drinkname;
    }

    public void setDrinkname(String name) {
        this.drinkname = name;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
