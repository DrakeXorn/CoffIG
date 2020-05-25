package business;

import dataAccess.*;
import model.*;
import model.exceptions.*;

import java.util.ArrayList;

public class CustomerManager {
    private CustomerDataAccess dao;

    public CustomerManager(){
        dao = new CustomerDBAccess();
    }

    public void addCustomer(Customer customer) throws AddDataException, ConnectionException, BusinessException {
        if (customer == null)
            throw new BusinessException("CustomerManager", "l'ajout d'un client", "Le client");
        dao.addCustomer(customer);
    }

    public ArrayList<Customer> getAllCustomers() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException, IntegerInputException{
        return dao.getAllCustomers();
    }

    public void updateCustomer(Customer customer) throws ModifyException, ConnectionException, BusinessException {
        if (customer == null)
            throw new BusinessException("CustomerManager", "la mise à jour d'un client", "Le client");
        dao.updateCustomer(customer);
    }

    public void removeCustomer(Customer customer) throws ConnectionException, ModifyException, BusinessException {
        if (customer == null)
            throw new BusinessException("CustomerManager", "la suppression d'un client", "Le client");
        dao.removeCustomer(customer);
    }
}
