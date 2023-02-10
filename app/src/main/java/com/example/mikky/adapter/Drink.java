package com.example.mikky.adapter;

public class Drink {

    private int drinkId;
    private String drinkName;
    private String drinkCategory;
    private String drinkImage;
    private String description;
    private Float price;

    public Drink() {
    }

    public Drink(int drinkId, String drinkName, String drinkCategory, String drinkImage, String description, Float price) {
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.drinkCategory = drinkCategory;
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

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkCategory() {
        return drinkCategory;
    }

    public void setDrinkCategory(String drinkCategory) {
        this.drinkCategory = drinkCategory;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
