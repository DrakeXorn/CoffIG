package controller;

import business.OrderManager;
import model.Order;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrderController {
    private OrderManager manager;

    public OrderController() {
        manager = new OrderManager();
    }

    public ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite)
            throws AllDataException, ConnectionException, StringInputException, IntegerInputException, DoubleInputException {
        return manager.searchOrders(customerId, startDate, endDate, isToTakeAway, isOnSite);
    }
}
