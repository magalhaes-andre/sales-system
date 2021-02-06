package com.magalhaes.andre.service;

import com.magalhaes.andre.entity.Product;
import com.magalhaes.andre.repository.ProductRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

public class ProductService {

    @Inject
    ProductRepository repository;


    public Uni<Product> createProduct(Product product) {
        repository.persist(product);
        return findByName(product.getName());
    }

    public Uni<Product> findByName(String name ){
        return repository.findByName(name)
                .onItem().ifNull().failWith(new NotFoundException())
                .onItem().castTo(Product.class);
    }

    public Multi<Product> filterByName(String name){
        return repository.filterByName(name)
                .onCompletion().ifEmpty().failWith(new NotFoundException());
    }
}