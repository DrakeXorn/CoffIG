package controller;

import business.CoffeeManager;
import model.Coffee;
import model.exceptions.*;

import java.util.ArrayList;

public class CoffeeController {
    private CoffeeManager coffeeManager;

    public CoffeeController() {
        coffeeManager = new CoffeeManager();
    }

    public ArrayList<Coffee> getAllCoffees() throws AllDataException, ConnectionException, DoubleInputException, IntegerInputException, DateException {
        return coffeeManager.getAllCoffees();
    }

    public void addCoffee(Coffee coffee) throws ConnectionException, AddDataException {
        coffeeManager.addCoffee(coffee);
    }

    public int getLastCoffeeID() throws ConnectionException, AddDataException {
        return coffeeManager.getLastCoffeeID();
    }

    public void updateCoffee(Coffee coffee) throws ConnectionException, ModifyException {
        coffeeManager.updateCoffee(coffee);
    }

    public ArrayList<String> getFeatures() throws AllDataException, ConnectionException {
        return coffeeManager.getFeatures();
    }
}
