package com.magalhaes.andre.repository;

import com.magalhaes.andre.entity.sale.Sale;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SaleRepository implements ReactivePanacheMongoRepository<Sale> {}
