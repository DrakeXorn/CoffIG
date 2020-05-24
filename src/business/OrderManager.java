package business;

import dataAccess.OrderDBAccess;
import dataAccess.OrderDataAccess;
import model.Drink;
import model.Food;
import model.Order;
import model.Topping;
import model.exceptions.*;
import test.LoyaltyCardPointsCalculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrderManager {
    private OrderDataAccess orderDataAccess;
    private LoyaltyCardPointsCalculator loyaltyCardPointsCalculator;

    public OrderManager() {
        orderDataAccess = new OrderDBAccess();
        loyaltyCardPointsCalculator = new LoyaltyCardPointsCalculator();
    }

    public void addOrder(Order order) throws ConnectionException, AddDataException, ModifyException {
        orderDataAccess.addOrder(order);
    }

    public ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite)
            throws AllDataException, ConnectionException, IntegerInputException, StringInputException, DoubleInputException {
        return orderDataAccess.searchOrders(customerId, startDate, endDate, isToTakeAway, isOnSite);
    }

    public Integer getLastOrderNumber() throws ConnectionException, AllDataException {
        return orderDataAccess.getLastOrderNumber();
    }

    public void updateLoyaltyCardPoints(String cardId, int numberPoints) throws ModifyException, ConnectionException {
        orderDataAccess.updateLoyaltyCardPoints(cardId,numberPoints);
    }

    public int addPoints(Integer loyaltyCardPoints, double orderPrice){
        return loyaltyCardPointsCalculator.add(loyaltyCardPoints, orderPrice);
    }

    public int removePoints(Integer loyaltyCardPoints, Integer advantagePoints){
        return loyaltyCardPointsCalculator.subtract(loyaltyCardPoints, advantagePoints);
    }

    public void removeRight(String loyaltyCardId, Integer advantageId) throws ConnectionException, ModifyException {
        orderDataAccess.removeRight(loyaltyCardId, advantageId);
    }

    public void closeConnexion() throws ClosedConnexion, ConnectionException {
        orderDataAccess.closeConnexion();
    }

    public ArrayList<Drink> getAllDrinks() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        return orderDataAccess.getAllDrinks();
    }

    public ArrayList<Food> getAllAvailableFoods() throws ConnectionException, DoubleInputException, DateException, IntegerInputException, AllDataException {
        return orderDataAccess.getAllAvailableFoods();
    }

    public ArrayList<Topping> getAllAvailableToppings() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        return orderDataAccess.getAllAvailableToppings();
    }
}
