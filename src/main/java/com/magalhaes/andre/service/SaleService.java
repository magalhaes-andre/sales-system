package com.magalhaes.andre.service;

        import com.magalhaes.andre.entity.Product;
        import com.magalhaes.andre.entity.sale.Sale;
        import com.magalhaes.andre.entity.Salesman;
        import com.magalhaes.andre.entity.sale.SaleRequest;
        import com.magalhaes.andre.exception.MissingInformationException;
        import com.magalhaes.andre.repository.SaleRepository;
        import io.smallrye.mutiny.Uni;
        import org.bson.types.ObjectId;

        import javax.enterprise.context.ApplicationScoped;
        import javax.inject.Inject;
        import java.util.ArrayList;
        import java.util.List;

        import static java.util.Objects.isNull;

@ApplicationScoped
public class SaleService {

    @Inject
    SaleRepository repository;
    @Inject
    SalesmanService salesmanService;
    @Inject
    ProductService productService;

    public Uni<Void> createSale(SaleRequest request) {
        Sale sale = new Sale();
        sale.setSalesman(populateSalesman(request.getSalesmanRegistration()).await().indefinitely());
        sale.setProducts(populateProducts(request.getProductsNames()));
        checkForCompleteInput(sale);
        sale.setSaleTotal(generateTotal(sale));
        return repository.persist(sale);
    }

    public Uni<Sale> findSaleById(ObjectId id){
        return repository.findById(id);
    }

    public Uni<List<Sale>> list(){
        return repository.listAll();
    }

    public Uni<Sale> updateSale(Sale updatedSale){
        return repository.update(updatedSale)
                .onItem().transformToUni(item -> findSaleById(updatedSale.getId()));
    }

    public Uni<Void> deleteSale(Sale sale){
        return  repository.delete(sale);
    }

    private Double generateTotal(Sale sale){
        return sale.getProducts().stream()
                .mapToDouble(product -> product.getPrice())
                .sum();
    }

    private Uni<Salesman> populateSalesman(Integer registration){
        return salesmanService.findByRegistration(registration);
    }

    private List<Product> populateProducts(List<String> productsNames){
        List<Product> products = new ArrayList<>();
        productsNames.forEach(product -> products.add(productService.findByName(product).await().indefinitely()));
        return products;
    }

    private void checkForCompleteInput(Sale sale){
        if (isNull(sale.getProducts()) || isNull(sale.getSalesman()) || sale.getProducts().isEmpty()){
            throw new MissingInformationException(sale);
        }
    }
}
