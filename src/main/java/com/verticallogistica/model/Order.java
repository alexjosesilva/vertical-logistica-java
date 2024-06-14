package com.verticallogistica.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String orderId;
    private double total;
    private LocalDate date;
    private List<Product> products;

    public Order(String orderId, double total, LocalDate date, List<Product> products) {
        this.orderId = orderId;
        this.total = total;
        this.date = date;
        this.products = products;
    }

    // Getters e setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
