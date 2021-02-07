package com.magalhaes.andre.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity
public class Product {

    private String name;
    private Double price;
    private Integer test;

    public Product() {}

    public Product(String name, double price, Integer test){
        this.name = name;
        this.price = price;
        this.test = test;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTest() { return test; }

    public void setTest(Integer test) { this.test = test; }
}
