package com.magalhaes.andre.service;

import com.magalhaes.andre.entity.Product;
import com.magalhaes.andre.exception.MissingInformationException;
import com.magalhaes.andre.repository.ProductRepository;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

import static java.util.Objects.isNull;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository repository;

    public Uni<Product> createProduct(Product product) {
        checkForCompleteInput(product);
        return repository.persist(product)
                .onItem().transformToUni(item -> findByName(product.getName()));
    }

    public Uni<Product> findByName(String name ){
        return repository.findByName(name)
                .onItem().ifNull().failWith(new NotFoundException());
    }

    public Uni<Product> findById(ObjectId id){
        return repository.findById(id)
                .onItem().ifNull().failWith(new NotFoundException());
    }

    public Uni<List<Product>> list(){
        return repository.listAll();
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