package dataAccess;

import model.*;
import model.exceptions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DataAccess {
    // create
    void addCustomer(Customer customer) throws AddException, ConnectionException;

    // read
    ArrayList<Locality> getAllLocalities() throws AllDataException, ConnectionException;
    ArrayList<Customer> getAllCustomers() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException, IntegerInputException;
    int getLastCustomerId() throws AllDataException, ConnectionException;
    ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite) throws AllDataException, ConnectionException, DoubleInputException, StringInputException, IntegerInputException;

    // update
    void modifyCustomer(Customer customer) throws ModifyException, ConnectionException;

    // delete

}
