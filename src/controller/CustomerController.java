package controller;

import business.CustomerManager;
import model.*;
import model.exceptions.*;
import java.util.ArrayList;

public class CustomerController {
    private CustomerManager manager;

    public CustomerController(){
        manager = new CustomerManager();
    }

    public void addCustomer(Customer customer) throws AddCustomerException, ConnectionException {
        manager.addCustomer(customer);
    }

    public ArrayList<Locality> getAllLocalities() throws AllDataException, ConnectionException {
        return manager.getAllLocalities();
    }
    public ArrayList<Customer> getAllCustomers() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException, IntegerInputException {
        return manager.getAllCustomers();
    }

    public void modifyCustomer(Customer customer) throws AddCustomerException, ConnectionException {
        manager.modifyCustomer(customer);
    }

}
