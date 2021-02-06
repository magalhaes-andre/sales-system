package java.com.magalhaes.andre.resource;

import java.com.magalhaes.andre.entity.Salesman;
import java.util.List;

public class SalesmanResource {

    public Salesman createSalesman(Salesman salesman){}
    public Salesman updateSalesman(long id, Salesman newSalesman){}
    public Salesman deleteSalesman(long id){}
    public Salesman findSalesmanByName(){}
    public Salesman findSalesmanByRegistration(){}
    public List<Salesman> listSalesmen(){}
    public List<Salesman> listSalesmenFilteredByName(){}
    public List<Salesman> listSalesmenFilteredByRegistration(){}
}
