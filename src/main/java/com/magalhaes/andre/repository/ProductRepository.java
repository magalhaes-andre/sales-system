package com.magalhaes.andre.repository;

import com.magalhaes.andre.entity.Product;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements ReactivePanacheMongoRepository<Product> {

    public Uni<Product> findByName(String name){
       return find("name", name).firstResult();
    }
}
