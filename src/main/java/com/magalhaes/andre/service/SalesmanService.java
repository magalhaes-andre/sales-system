package com.magalhaes.andre.service;

import com.magalhaes.andre.entity.Salesman;
import com.magalhaes.andre.exception.MissingInformationException;
import com.magalhaes.andre.repository.SalesmanRepository;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.util.List;

import static java.util.Objects.isNull;

@ApplicationScoped
public class SalesmanService {

    @Inject
    private SalesmanRepository repository;

    public Uni<Salesman> createSalesman(Salesman salesman) {
        checkForCompleteInput(salesman);
        return repository.persist(salesman)
                .onItem().transformToUni(item -> findByRegistration(salesman.getRegistration()));
    }

    public Uni<Salesman> findByRegistration(Integer registration){
        return repository.findByRegistration(registration)
                .onItem().ifNull().failWith(new InternalServerErrorException());
    }

    public Uni<List<Salesman>> list(){
        return repository.listAll();
    }

    public Uni<Salesman> updateSalesman(Salesman updatedSalesman){
        return repository.update(updatedSalesman)
                .onItem().transformToUni(item -> findByRegistration(updatedSalesman.getRegistration()));
    }

    public Uni<Void> deleteSalesman(Salesman salesman){
        return  repository.delete(salesman);
    }

    private void checkForCompleteInput(Salesman salesman){
        if (isNull(salesman.getName()) || isNull(salesman.getRegistration())){
            throw new MissingInformationException(salesman);
        }
    }
}