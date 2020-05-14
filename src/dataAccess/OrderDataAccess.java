package dataAccess;

import model.Order;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface OrderDataAccess {
    boolean addOrder(Order order) throws ConnectionException, AddDataException;
    Integer getLastOrderNumber() throws ConnectionException, AddDataException;
    ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite) throws AllDataException, ConnectionException, DoubleInputException, StringInputException, IntegerInputException;
    String updatePointsToLoyaltyCard(String cardId, int numberPoints) throws AllDataException, ConnectionException;
    int getPointsLoyaltyCard(String cardId) throws AllDataException, ConnectionException;
    ArrayList<Integer> getPointsAdvantage(String cardId) throws AllDataException, ConnectionException;
    void updateStockLocation(Integer alley, Integer shelf, Integer number, Integer removeQuantity) throws AllDataException, ConnectionException;
    Integer quantityStockLocation(Integer alley, Integer shelf, Integer number) throws AllDataException, ConnectionException;

    }
