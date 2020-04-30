package business;

import dataAccess.*;
import model.*;
import model.exceptions.*;

import java.util.ArrayList;

public class CustomerManager {
    private DataAccess dao;

    public CustomerManager(){
        dao = new CustomerDBAccess();
    }

    public void addCustomer(Customer customer) throws AddCustomerException, ConnectionException {
        dao.addCustomer(customer);
    }

    public ArrayList<Locality> getAllLocalities() throws AllDataException, ConnectionException {
        return dao.getAllLocalities();
    }

    public ArrayList<Customer> getAllCustomers() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException, IntegerInputException{
        return dao.getAllCustomers();
    }


    public void modifyCustomer(Customer customer) throws AddCustomerException, ConnectionException{
        dao.modifyCustomer(customer);
    }

}
