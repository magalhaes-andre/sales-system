package com.magalhaes.andre.resource;

import com.magalhaes.andre.entity.sale.Sale;
import com.magalhaes.andre.entity.sale.SaleRequest;
import com.magalhaes.andre.service.SaleService;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/sales")
@RequestScoped
public class SaleResource {

    @Inject
    private SaleService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> createSale(SaleRequest request){ return service.createSale(request); }

    @GET
    @Path("{id}")
    public Uni<Sale> findSaleById(@PathParam("id") ObjectId id){ return service.findSaleById(id); }

    @GET
    public Uni<List<Sale>> listSales(){ return service.list(); }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Sale> updateSale(Sale updatedSale){ return service.updateSale(updatedSale); }

    @DELETE
    public Uni<Void> deleteSale(Sale sale){ return service.deleteSale(sale); }
}
