package com.magalhaes.andre.service;

import com.magalhaes.andre.entity.Salesman;
import com.magalhaes.andre.entity.sale.Sale;
import com.magalhaes.andre.exception.MissingInformationException;
import com.magalhaes.andre.repository.SalesmanRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import java.util.*;
import java.util.function.Function;

import static java.util.Objects.isNull;

@ApplicationScoped
public class SalesmanService {

    @Inject
    private SalesmanRepository repository;
    @Inject
    private SaleService saleService;

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

    public Uni<Map<String, Double>> listSalesmanBySaleQuantity(){
        Map<String, Double> salesmen = new HashMap<>();
        List<Sale> sales = saleService.list().await().indefinitely();

        toMap(salesmen, sales, (sale) -> 1d, (sale) -> salesmen.get(sale.getSalesman().getName()) + 1);
        return Uni.createFrom().item(salesmen);
    }

   public Uni<Map<String, Double>> listSalesmanBySaleTotal(){
       Map<String, Double> salesmen = new HashMap<>();
       List<Sale> sales = saleService.list().await().indefinitely();

       toMap(salesmen, sales, (sale) -> sale.getSaleTotal(), (sale) -> salesmen.get(sale.getSalesman().getName()) + sale.getSaleTotal());

       return Uni.createFrom().item(salesmen);
   }

    private void toMap(Map<String, Double> salesmen, List<Sale> sales, Function<Sale, Double> identityProvider, Function<Sale, Double> mapFunction) {
        sales.forEach(sale -> {
            if(!salesmen.containsKey(sale.getSalesman().getName())){
                salesmen.put(sale.getSalesman().getName(), identityProvider.apply(sale));
            } else{
                salesmen.replace(sale.getSalesman().getName(), mapFunction.apply(sale));
            }
        });
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