package dataAccess;

import model.*;
import model.exceptions.*;

import java.util.ArrayList;

public interface CustomerDataAccess {
    // create
    void addCustomer(Customer customer) throws AddDataException, ConnectionException;

    // read
    ArrayList<Customer> getAllCustomers() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException, IntegerInputException;

    // update
    void updateCustomer(Customer customer) throws ModifyException, ConnectionException;

    // delete
    void removeCustomer(Customer customer) throws ModifyException, ConnectionException;
}
