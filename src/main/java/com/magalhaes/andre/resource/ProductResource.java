package com.magalhaes.andre.resource;

import com.magalhaes.andre.entity.Product;
import com.magalhaes.andre.service.ProductService;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/products")
@RequestScoped
public class ProductResource {

    @Inject
    private ProductService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Product> createProduct(Product product){
        return service.createProduct(product);
    }

    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Product> findProductByName(@PathParam("name") String name) {  return service.findByName(name); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Product>> listProducts(){ return service.list(); }

    @Path("/quantity")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Map<String, Integer>> listProductsByQuantitySelled(){ return service.listProductsByQuantitySelled(); }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Product> updateProduct(Product product){ return service.updateProduct(product); }

    @DELETE
    public Uni<Void> deleteProduct(Product product){ return service.deleteProduct(product); }
}
