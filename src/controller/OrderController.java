package controller;

import business.OrderManager;
import model.Drink;
import model.Food;
import model.Order;
import model.Topping;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrderController {
    private OrderManager manager;

    public OrderController() {
        manager = new OrderManager();
    }

    public void addOrder(Order order) throws ConnectionException, AddDataException, ModifyException {
        manager.addOrder(order);
    }

    public ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite)
            throws AllDataException, ConnectionException, StringInputException, IntegerInputException, DoubleInputException {
        return manager.searchOrders(customerId, startDate, endDate, isToTakeAway, isOnSite);
    }

    public Integer getLastOrderNumber() throws ConnectionException, AllDataException {
        return manager.getLastOrderNumber();
    }

    public void updateLoyaltyCardPoints(String cardId, int numberPoints) throws ModifyException, ConnectionException {
        manager.updateLoyaltyCardPoints(cardId,numberPoints);
    }

    public int addPoints(Integer loyaltyCardPoints, double orderPrice){
        return manager.addPoints(loyaltyCardPoints, orderPrice);
    }

    public int removePoints(Integer loyaltyCardPoints, Integer advantagePoints){
        return manager.removePoints(loyaltyCardPoints, advantagePoints);
    }

    public void removeRight(String loyaltyCardId, Integer advantageId) throws ConnectionException, ModifyException {
        manager.removeRight(loyaltyCardId, advantageId);
    }

    public void closeConnexion() throws ClosedConnexion, ConnectionException{
        manager.closeConnexion();
    }

    public ArrayList<Drink> getAllDrinks() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        return manager.getAllDrinks();
    }

    public ArrayList<Food> getAllAvailableFoods() throws ConnectionException, DoubleInputException, DateException, IntegerInputException, AllDataException {
        return manager.getAllAvailableFoods();
    }

    public ArrayList<Topping> getAllAvailableToppings() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        return manager.getAllAvailableToppings();
    }
}
