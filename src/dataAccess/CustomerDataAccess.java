package dataAccess;

import model.*;
import model.exceptions.*;

import java.util.ArrayList;

public interface CustomerDataAccess {
    // create
    void addCustomer(Customer customer) throws AddException, ConnectionException;

    // read
    ArrayList<Locality> getAllLocalities() throws AllDataException, ConnectionException;
    ArrayList<Customer> getAllCustomers() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException, IntegerInputException;
    int getLastCustomerId() throws AllDataException, ConnectionException;

    // update
    void modifyCustomer(Customer customer) throws ModifyException, ConnectionException;

    // delete
    void removeCustomer(Customer customer) throws ModifyException, ConnectionException;
}
