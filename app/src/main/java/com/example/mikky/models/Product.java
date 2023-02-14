package com.example.mikky.models;

import java.util.ArrayList;

public class Product {

    private float productId;

    private String productName;
    private float supplierId;
    private float categoryId;
    private String quantityPerUnit;
    private float unitPrice;
    private float unitsInStock;
    private float unitsOnOrder;
    private float reorderLevel;
    private boolean discontinued;
    private String category = null;
    private String supplier = null;
    ArrayList < Object > orderDetails = new ArrayList< Object >();


    // Getter Methods

    public float getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public float getSupplierId() {
        return supplierId;
    }

    public float getCategoryId() {
        return categoryId;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public float getUnitsInStock() {
        return unitsInStock;
    }

    public float getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public float getReorderLevel() {
        return reorderLevel;
    }

    public boolean getDiscontinued() {
        return discontinued;
    }

    public String getCategory() {
        return category;
    }

    public String getSupplier() {
        return supplier;
    }

    // Setter Methods

    public void setProductId(float productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSupplierId(float supplierId) {
        this.supplierId = supplierId;
    }

    public void setCategoryId(float categoryId) {
        this.categoryId = categoryId;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitsInStock(float unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public void setUnitsOnOrder(float unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public void setReorderLevel(float reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
