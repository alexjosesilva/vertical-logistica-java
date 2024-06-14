package com.verticallogistica.model;

import java.util.List;

public class User {
    private String userId;
    private String name;
    private List<Order> orders;

    public User(String userId, String name, List<Order> orders) {
        this.userId = userId;
        this.name = name;
        this.orders = orders;
    }

    // Getters e setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}