package dataAccess;

import model.*;
import model.exceptions.*;

import java.util.ArrayList;

public interface CustomerDataAccess {
    void addCustomer(Customer customer) throws AddDataException, ConnectionException;
    ArrayList<Customer> getAllCustomers() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException, IntegerInputException;
    void updateCustomer(Customer customer) throws ModifyException, ConnectionException;
    void removeCustomer(Customer customer) throws ModifyException, ConnectionException;
}
