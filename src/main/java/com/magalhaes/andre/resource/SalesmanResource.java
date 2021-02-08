package com.magalhaes.andre.resource;

import com.magalhaes.andre.entity.Salesman;
import com.magalhaes.andre.service.SalesmanService;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/salesmen")
@RequestScoped
public class SalesmanResource {

    @Inject
    private SalesmanService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Salesman> createSalesman(Salesman salesman){
        return service.createSalesman(salesman);
    }

    @Path("/{registration}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Salesman> findSalesmanByRegistration(@PathParam("registration") Integer registration) {  return service.findByRegistration(registration); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Salesman>> listSalesmen(){ return service.list(); }

    @Path("/quantity")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Map<String, Integer>> listSalesmanBySaleQuantity(){ return service.listSalesmanBySaleQuantity(); }

    @Path("/total")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Map<String, Double>> listSalesmanBySaleTotal(){ return service.listSalesmanBySaleTotal(); }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Salesman> updateSalesman(Salesman salesman){ return service.updateSalesman(salesman); }

    @DELETE
    public Uni<Void> deleteSalesman(Salesman salesman){ return service.deleteSalesman(salesman); }
}