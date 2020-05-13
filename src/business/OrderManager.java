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

    public String addPointsToLoyaltyCard(String cardId, double orderPrice) throws AllDataException, ConnectionException {
        int price = (int) orderPrice;

        if (cardId != null && price > 0)
            return orderDataAccess.updatePointsToLoyaltyCard(cardId, price * 10);
        else
            return "Les points n'ont pas été ajouté à la carte de fidélité !";
    }

    public String removePointsToLoyaltyCard(String cardId, int numberPoints) throws AllDataException, ConnectionException {
        if (cardId != null && numberPoints > 0)
            return orderDataAccess.updatePointsToLoyaltyCard(cardId, -numberPoints);
        else
            return "Les points n'ont pas été supprimé de la carte de fidélité !";
    }

    public boolean updateStockLocation(Integer alley, Integer shelf, Integer number, Integer removeQuantity) throws AllDataException, ConnectionException {
        if(removeQuantity > 0)
            orderDataAccess.updateStockLocation(alley, shelf, number, removeQuantity);
        return orderDataAccess.isEmptyStockLocation(alley, shelf, number);
    }


}
