package com.magalhaes.andre.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;

@MongoEntity
public class Product {

    private ObjectId id;
    private String name;
    private Double price;

    public Product() {}

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    public Product(ObjectId id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ObjectId getId() { return id; }

    public void setId(ObjectId id) { this.id = id; }

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
}
