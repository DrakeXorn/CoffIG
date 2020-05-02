package dataAccess;

import model.Coffee;
import model.exceptions.AddCoffeeException;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public interface CoffeeDataAccess {
    ArrayList<Coffee> getAllCoffees() throws AllDataException, ConnectionException;
    boolean addCoffee(Coffee coffee) throws ConnectionException, AddCoffeeException;
    boolean removeCoffee(Coffee coffee) throws ConnectionException, AddCoffeeException;
    boolean updateCoffee(Coffee coffee);
    int getLastId() throws ConnectionException, AddCoffeeException;
}
