package business;

import dataAccess.*;
import model.*;
import model.exceptions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CustomerManager {
    private DataAccess dao;

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
<<<<<<< HEAD
    public int getLastCustomerId() throws AllDataException, ConnectionException {
        return dao.getLastCustomerId();
    }
    public void modifyCustomer(Customer customer) throws ModifyException, ConnectionException {
        dao.modifyCustomer(customer);
    }
    public ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite)
            throws AllDataException, ConnectionException, IntegerInputException, StringInputException, DoubleInputException {
        return dao.searchOrders(customerId, startDate, endDate, isToTakeAway, isOnSite);
    }

=======

    public void modifyCustomer(Customer customer) throws AddCustomerException, ConnectionException{
        dao.modifyCustomer(customer);
    }
>>>>>>> 5dd7335b6321737fe7fdcb23581e5105ed2e6c5e
}
