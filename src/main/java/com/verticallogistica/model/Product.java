package com.verticallogistica.model;

public class Product {
    private String productId;
    private double value;

    public Product(String productId, double value) {
        this.productId = productId;
        this.value = value;
    }

    // Getters e setters

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
