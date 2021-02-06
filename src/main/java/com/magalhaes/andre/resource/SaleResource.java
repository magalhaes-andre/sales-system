package java.com.magalhaes.andre.resource;

import java.com.magalhaes.andre.entity.Sale;
import java.util.List;

public class SaleResource {

    public Sale createSale(Sale sale){}
    public Sale updateSale(long id, Sale newSale){}
    public Sale deleteSale(long id){}
    public Sale findSaleByName(){}
    public Sale findSaleByRegistration(){}
    public List<Sale> listSale(){}
    public List<Sale> listSaleFilteredByName(){}
    public List<Sale> listSaleFilteredByRegistration(){}
}
