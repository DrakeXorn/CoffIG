package dataAccess;

import model.Coffee;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface CoffeeDataAccess {
    ArrayList<Coffee> getAllCoffees() throws AllDataException, ConnectionException, DoubleInputException, IntegerInputException;
    ArrayList<Coffee> getCoffees(GregorianCalendar startDate, GregorianCalendar endDate, String originCountry, boolean areInGrains, boolean areEnvironmentFriendly, double price, double packaging);
    boolean addCoffee(Coffee coffee) throws ConnectionException, AddDataException;
    boolean removeCoffee(Coffee coffee) throws ConnectionException, AddDataException;
    boolean updateCoffee(Coffee coffee) throws ConnectionException, AddDataException;
    int getLastId() throws ConnectionException, AddDataException;
}
