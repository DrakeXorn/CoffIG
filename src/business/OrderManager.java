package business;

import dataAccess.OrderDBAccess;
import dataAccess.OrderDataAccess;
import model.Drink;
import model.Food;
import model.Order;
import model.Topping;
import model.exceptions.*;
import business.test.LoyaltyCardPointsCalculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrderManager {
    private OrderDataAccess orderDataAccess;
    private LoyaltyCardPointsCalculator loyaltyCardPointsCalculator;

    public OrderManager() {
        orderDataAccess = new OrderDBAccess();
        loyaltyCardPointsCalculator = new LoyaltyCardPointsCalculator();
    }

    public void addOrder(Order order) throws ConnectionException, AddDataException, ModifyException, BusinessException {
        if (order == null)
            throw new BusinessException("OrderManager", "l'ajout d'une commande", "La commande");

        orderDataAccess.addOrder(order);
    }

    public ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite)
            throws AllDataException, ConnectionException, IntegerInputException, StringInputException, DoubleInputException, BusinessException {
        if (customerId == null)
            throw new BusinessException("OrderManager", "La recherche des anciennes commandes", "L'identifiant utilisateur");
        if (startDate == null)
            throw new BusinessException("OrderManager", "La recherche des anciennes commandes", "La date de début");
        if (endDate == null)
            throw new BusinessException("OrderManager", "La recherche des anciennes commandes", "La date de fin");
        if (isToTakeAway == null)
            throw new BusinessException("OrderManager", "La recherche des anciennes commandes", "Le critère \"À emporter\"");
        if (isOnSite == null)
            throw new BusinessException("OrderManager", "La recherche des anciennes commandes", "Le critère \"À consommer sur placer\"");

        return orderDataAccess.searchOrders(customerId, startDate, endDate, isToTakeAway, isOnSite);
    }

    public Integer getLastOrderNumber() throws ConnectionException, AllDataException {
        return orderDataAccess.getLastOrderNumber();
    }

    public void updateLoyaltyCardPoints(String cardId, int numberPoints) throws ModifyException, ConnectionException, BusinessException {
        if (cardId == null)
            throw new BusinessException("OrderManager", "la mise à jour des points de la carte de fidélité", "Le numéro de la carte de fidélité");

        orderDataAccess.updateLoyaltyCardPoints(cardId,numberPoints);
    }

    public int addPoints(Integer loyaltyCardPoints, double orderPrice) throws BusinessException {
        if (loyaltyCardPoints == null)
            throw new BusinessException("OrderManager", "L'ajout de points à la carte de fidélité", "Le nombre de points de la carte de fidélité");

        return loyaltyCardPointsCalculator.add(loyaltyCardPoints, orderPrice);
    }

    public int removePoints(Integer loyaltyCardPoints, Integer advantagePoints) throws BusinessException {
        if (loyaltyCardPoints == null)
            throw new BusinessException("OrderManager", "Le retrait de points de la carte de fidélité", "Le nombre de points de la carte de fidélité");

        return loyaltyCardPointsCalculator.subtract(loyaltyCardPoints, advantagePoints);
    }

    public void removeRight(String loyaltyCardId, Integer advantageId) throws ConnectionException, ModifyException, BusinessException {
        if (loyaltyCardId == null)
            throw new BusinessException("OrderManager", "Le retrait de l'avantage de la carte de fidélité", "Le numéro de la carte de fidélité");

        orderDataAccess.removeRight(loyaltyCardId, advantageId);
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

    public void closeConnexion() throws ClosedConnexion, ConnectionException {
        orderDataAccess.closeConnexion();
    }
}
