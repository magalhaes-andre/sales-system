package com.magalhaes.andre.resource;

import com.magalhaes.andre.entity.Product;
import com.magalhaes.andre.entity.Salesman;
import com.magalhaes.andre.service.ProductService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.ArrayList;
import java.util.List;

public class ProductResource {

    ProductService service;

    public Uni<Product> createProduct(Product product){
        return service.createProduct(product);
    }
    public Product updateProduct(long id, Product newProduct){}
    public Product deleteProduct(long id){}

    public Uni<Product> findProductByName(String name){
        return service.findByName(name);
    }

    public Product findProductByRegistration(){
        return new Product();
    }
    public ArrayList<Salesman> listSalesmen(){
        return new ArrayList<Salesman>();
    }
    public Multi<Product> listSalesmenFilteredByName(String name){
        return service.filterByName(name);
    }
    public List<Product> listSalesmenFilteredByRegistration(){}
}
