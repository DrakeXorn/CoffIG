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

    public void addCustomer(Customer customer) throws AddException, ConnectionException {
        dao.addCustomer(customer);
    }
    public ArrayList<Locality> getAllLocalities() throws AllDataException, ConnectionException {
        return dao.getAllLocalities();
    }
    public ArrayList<Customer> getAllCustomers() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException, IntegerInputException{
        return dao.getAllCustomers();
    }

    public int getLastCustomerId() throws AllDataException, ConnectionException {
        return dao.getLastCustomerId();
    }
    public void updateCustomer(Customer customer) throws ModifyException, ConnectionException {
        dao.updateCustomer(customer);
    }

    public void removeCustomer(Customer customer) throws ConnectionException, ModifyException {
        dao.removeCustomer(customer);
    }
}
