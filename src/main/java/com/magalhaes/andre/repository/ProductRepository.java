package com.magalhaes.andre.repository;

import com.magalhaes.andre.entity.Product;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.Optional;

public class ProductRepository implements ReactivePanacheMongoRepository<Product> {

    public Uni<Optional<Product>> findByName(String name){
        return find("name", name).firstResultOptional();
    }

    public Multi<Product> filterByName(String name){
        return find("name", name).stream();
    }
}
