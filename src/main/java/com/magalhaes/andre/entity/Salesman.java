package com.magalhaes.andre.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.ProjectionFor;

@MongoEntity
@ProjectionFor(Salesman.class)
public class Salesman {

    private Integer registration;
    private String name;

    public Salesman() {}

    public Salesman(Integer registration, String name) {
        this.registration = registration;
        this.name = name;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
