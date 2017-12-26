package com.andersen.spring.entity;

import java.net.PortUnreachableException;

public class Product {

    private long id;
    private String title;
    private String description;
    private double price;
    private long userId;

    public Product() {

    }

    public Product(String title, String description, double price, long userId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
