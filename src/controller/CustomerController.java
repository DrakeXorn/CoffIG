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

    public void addCustomer(Customer customer) throws AddDataException, ConnectionException {
        manager.addCustomer(customer);
    }

    public ArrayList<Customer> getAllCustomers() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException, IntegerInputException {
        return manager.getAllCustomers();
    }

    public void updateCustomer(Customer customer) throws ModifyException, ConnectionException {
        manager.updateCustomer(customer);
    }

    public void removeCustomer(Customer customer) throws ConnectionException, ModifyException {
        manager.removeCustomer(customer);
    }
}
