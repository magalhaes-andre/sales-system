package com.magalhaes.andre.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Product{

    private String name;
    private Double price;

    public String getName(){
        return name;
    };
}
