package com.example.mikky.adapter;

public class Drink {

    private int drinkId;
    private String drinkName;
    private float price;

    public Drink(int drinkId, String drinkName, Float price) {
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.price = price;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
