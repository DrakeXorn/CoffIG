package dataAccess;

import model.Drink;
import model.Food;
import model.Order;
import model.Topping;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface OrderDataAccess {
    void addOrder(Order order) throws ConnectionException, AddDataException, ModifyException;
    Integer getLastOrderNumber() throws ConnectionException, AllDataException;
    ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite) throws AllDataException, ConnectionException, DoubleInputException, StringInputException, IntegerInputException;
    void updateLoyaltyCardPoints(String cardId, int numberPoints) throws ModifyException, ConnectionException;
    Integer getPointsAdvantage(Integer advantageId) throws AllDataException, ConnectionException;
    void removeRight(String loyaltyCardId, Integer avantageId) throws ConnectionException, ModifyException;
    void closeConnexion() throws ClosedConnexion, ConnectionException;
    ArrayList<Drink> getAllDrinks() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException;
    ArrayList<Food> getAllAvailableFoods() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException;
    ArrayList<Topping> getAllAvailableToppings() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException;

}
