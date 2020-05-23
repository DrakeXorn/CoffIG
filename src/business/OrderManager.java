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

    public void addOrder(Order order) throws ConnectionException, AddDataException {
        orderDataAccess.addOrder(order);
    }

    public ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite)
            throws AllDataException, ConnectionException, IntegerInputException, StringInputException, DoubleInputException {
        return orderDataAccess.searchOrders(customerId, startDate, endDate, isToTakeAway, isOnSite);
    }

    public Integer getLastOrderNumber() throws ConnectionException, AddDataException {
        return orderDataAccess.getLastOrderNumber();
    }

    public String addPointsToLoyaltyCard(String cardId, double orderPrice) throws ModifyException, ConnectionException {
        return (cardId != null && (int) orderPrice > 0) ?
                orderDataAccess.updatePointsToLoyaltyCard(cardId, (int) orderPrice * 10) :
                "Les points n'ont pas été ajoutés à la carte de fidélité !";
    }

    public String removePointsToLoyaltyCard(String cardId, int numberPoints) throws ModifyException, ConnectionException {
        return (cardId != null && numberPoints > 0) ?
                orderDataAccess.updatePointsToLoyaltyCard(cardId, -numberPoints) :
                "Les points n'ont pas été supprimés de la carte de fidélité !";
    }

    public void removeRight(String loyaltyCardId, Integer advantageId) throws ConnectionException, ModifyException {
        orderDataAccess.removeRight(loyaltyCardId, advantageId);
    }

    public void closeConnexion() throws ClosedConnexion, ConnectionException {
        orderDataAccess.closeConnexion();
    }
}
