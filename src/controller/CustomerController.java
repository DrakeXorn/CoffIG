package controller;

import business.CustomerManager;
import model.Customer;
import model.exceptions.AddCustomerException;
import model.exceptions.ConnectionException;

public class CustomerController {
    private CustomerManager manager;

    public CustomerController(){
        manager = new CustomerManager();
    }

    public void addCustomer(Customer customer) throws AddCustomerException, ConnectionException {
        manager.addCustomer(customer);
    }

}
