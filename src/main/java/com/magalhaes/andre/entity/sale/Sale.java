package com.magalhaes.andre.entity.sale;

import com.magalhaes.andre.entity.Product;
import com.magalhaes.andre.entity.Salesman;
import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;

import java.util.List;

@MongoEntity
public class Sale {

    private ObjectId id;
    private Salesman salesman;
    private List<Product> products;
    private Double saleTotal;

    public Sale() {}

    public Sale(ObjectId id, Salesman salesman, List<Product> products, Double saleTotal) {
        this.id = id;
        this.salesman = salesman;
        this.products = products;
        this.saleTotal = saleTotal;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(Double saleTotal) {
        this.saleTotal = saleTotal;
    }
}
