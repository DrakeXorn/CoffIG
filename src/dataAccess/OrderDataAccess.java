package dataAccess;

import model.Order;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface OrderDataAccess {
    ArrayList<Order> getAllOrders() throws AllDataException;
    boolean addOrder(Order order) throws ConnectionException, AddDataException;
    boolean removeOrder(Order order) throws ConnectionException, AddDataException;
    boolean updateOrder(Order order) throws ConnectionException, AddDataException;
    Integer getLastOrderNumber() throws ConnectionException, AddDataException;
    ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite) throws AllDataException, ConnectionException, DoubleInputException, StringInputException, IntegerInputException;
}
