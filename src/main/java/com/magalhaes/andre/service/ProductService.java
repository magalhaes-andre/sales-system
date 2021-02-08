package com.magalhaes.andre.service;

import com.magalhaes.andre.entity.Product;
import com.magalhaes.andre.entity.sale.Sale;
import com.magalhaes.andre.exception.MissingInformationException;
import com.magalhaes.andre.repository.ProductRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ServiceUnavailableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@ApplicationScoped
public class ProductService {

    @Inject
    private ProductRepository repository;
    @Inject
    private SaleService saleService;

    public Uni<Product> createProduct(Product product) {
        checkForCompleteInput(product);
        return repository.persist(product)
                .onItem().transformToUni(item -> findByName(product.getName()));
    }

    public Uni<Product> findByName(String name ){
        return repository.findByName(name)
                .onItem().ifNull().failWith(new ServiceUnavailableException());
    }

    public Uni<List<Product>> list(){
        return repository.listAll();
    }

    public Uni<Map<String, Integer>> listProductsByQuantitySelled(){
        Map<String, Integer> productsByQuantitySelled = new HashMap<>();
        List<String> productsSelled = new ArrayList<>();
        List<Sale> sales = saleService.list().await().indefinitely();

        sales.forEach(sale -> {
            sale.getProducts().forEach(product -> productsSelled.add(product.getName()));
        });

        productsSelled.forEach(product -> {
            if(!productsByQuantitySelled.containsKey(product)){
                productsByQuantitySelled.put(product, 1);
            } else {
                productsByQuantitySelled.replace(product, productsByQuantitySelled.get(product) + 1);
            }
        });

        return Uni.createFrom().item(productsByQuantitySelled);
    }

    public Uni<Product> updateProduct(Product updatedProduct){
        return repository.update(updatedProduct)
                .onItem().transformToUni(item -> findByName(updatedProduct.getName()));
    }

    public Uni<Void> deleteProduct(Product product){
        return  repository.delete(product);
    }

    private void checkForCompleteInput(Product product){
        if (isNull(product.getName()) || isNull(product.getPrice())){
            throw new MissingInformationException(product);
        }
    }
}