package com.magalhaes.andre.repository;

import com.magalhaes.andre.entity.Salesman;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SalesmanRepository implements ReactivePanacheMongoRepository<Salesman> {

    public Uni<Salesman> findByRegistration (Integer registration){
        return find("registration", registration).firstResult();
    }
}
