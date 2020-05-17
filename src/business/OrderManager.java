package business;

import dataAccess.OrderDBAccess;
import dataAccess.OrderDataAccess;
import model.Order;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrderManager {
    private OrderDataAccess orderDataAccess;

    public OrderManager() {
        orderDataAccess = new OrderDBAccess();
    }

    public ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite)
            throws AllDataException, ConnectionException, IntegerInputException, StringInputException, DoubleInputException {
        return orderDataAccess.searchOrders(customerId, startDate, endDate, isToTakeAway, isOnSite);
    }

    public Integer getLastOrderNumber() throws ConnectionException, AddDataException {
        return orderDataAccess.getLastOrderNumber();
    }
}
