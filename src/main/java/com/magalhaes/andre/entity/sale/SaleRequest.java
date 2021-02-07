package com.magalhaes.andre.entity.sale;

import java.util.List;

public class SaleRequest {

    private Integer salesmanRegistration;
    private List<String> productsNames;

    public SaleRequest() {}

    public SaleRequest(Integer salesmanRegistration, List<String> productsNames) {
        this.salesmanRegistration = salesmanRegistration;
        this.productsNames = productsNames;
    }

    public Integer getSalesmanRegistration() {
        return salesmanRegistration;
    }

    public void setSalesmanRegistration(Integer salesmanRegistration) {
        this.salesmanRegistration = salesmanRegistration;
    }

    public List<String> getProductsNames() {
        return productsNames;
    }

    public void setProductsNames(List<String> productsNames) {
        this.productsNames = productsNames;
    }
}
