package dataAccess;

import model.Order;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface OrderDataAccess {
    void addOrder(Order order) throws ConnectionException, AddDataException, ModifyException;
    Integer getLastOrderNumber() throws ConnectionException, AllDataException;
    ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite) throws AllDataException, ConnectionException, DoubleInputException, StringInputException, IntegerInputException;
    String updatePointsToLoyaltyCard(String cardId, int numberPoints) throws ModifyException, ConnectionException;
    int getPointsLoyaltyCard(String cardId) throws AllDataException, ConnectionException;
    ArrayList<Integer> getPointsAdvantage(String cardId) throws AllDataException, ConnectionException;
    void removeRight(String loyaltyCardId, Integer avantageId) throws ConnectionException, ModifyException;
    void closeConnexion() throws ClosedConnexion, ConnectionException;
}
