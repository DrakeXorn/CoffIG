package dataAccess;

import model.Customer;
import model.exceptions.AddCustomerException;
import model.exceptions.ConnectionException;

public interface CustomerDataAccess {
    void addCustomer(Customer customer) throws AddCustomerException, ConnectionException; // create
    // read
    // update
    // delete

}
