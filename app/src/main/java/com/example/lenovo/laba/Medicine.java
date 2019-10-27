package com.example.lenovo.laba;

import java.io.Serializable;

public class Medicine implements Serializable {
    private String Name;
    private double Price;
    private String Producer;
    private String Description;

    public Medicine() {}
    public Medicine(String name, double price, String producer, String description) {
        this.Name = name;
        this.Price = price;
        this.Producer = producer;
        this.Description = description;
    }

    public double getPrice() {
        return Price;
    }

    public String getName() {
        return Name;
    }

    public String getProducer() {
        return Producer;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setProducer(String producer) {
        Producer = producer;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
