package dataAccess;

import model.*;
import model.exceptions.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DataAccess {
    // create
    void addCustomer(Customer customer) throws AddCustomerException, ConnectionException;

    // read
    ArrayList<Locality> getAllLocalities() throws AllDataException, ConnectionException;
    ArrayList<Customer> getAllCustomers() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException, IntegerInputException;

    // update
    void modifyCustomer(Customer customer) throws AddCustomerException, ConnectionException;

    // delete

}
