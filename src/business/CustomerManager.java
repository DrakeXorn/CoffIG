package business;

import dataAccess.CustomerDBAccess;
import dataAccess.CustomerDataAccess;
import model.Customer;
import model.exceptions.AddCustomerException;
import model.exceptions.ConnectionException;

public class CustomerManager {
    private CustomerDataAccess dao;

    public CustomerManager(){
        dao = new CustomerDBAccess();
    }

    public void addCustomer(Customer customer) throws AddCustomerException, ConnectionException {
        dao.addCustomer(customer);
    }
}
