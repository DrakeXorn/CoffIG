package dataAccess;

import model.Order;
import model.exceptions.AddDataException;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public interface OrderDataAccess {
    ArrayList<Order> getAllOrders() throws AllDataException;
    boolean addOrder(Order order) throws ConnectionException, AddDataException;
    boolean removeOrder(Order order) throws ConnectionException, AddDataException;
    boolean updateOrder(Order order) throws ConnectionException, AddDataException;
    Integer getLastOrderNumber() throws ConnectionException, AddDataException;
}
