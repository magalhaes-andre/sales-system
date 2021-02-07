package com.magalhaes.andre.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.ProjectionFor;
import org.bson.types.ObjectId;

@MongoEntity
public class Salesman {

    private ObjectId id;
    private Integer registration;
    private String name;

    public Salesman() {}

    public Salesman(Integer registration, String name) {
        this.registration = registration;
        this.name = name;
    }

    public Salesman(ObjectId id, Integer registration, String name) {
        this.id = id;
        this.registration = registration;
        this.name = name;
    }

    public ObjectId getId() { return id; }

    public void setId(ObjectId id) { this.id = id; }

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
