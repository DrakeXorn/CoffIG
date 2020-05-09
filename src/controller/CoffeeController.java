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

    public ArrayList<Coffee> getAllCoffees() throws AllDataException, ConnectionException, DoubleInputException, IntegerInputException {
        return coffeeManager.getAllCoffees();
    }

    public boolean addCoffee(Coffee coffee) throws ConnectionException, AddDataException {
        return coffeeManager.addCoffee(coffee);
    }

    public int getNbrCoffees() throws ConnectionException, AddDataException {
        return coffeeManager.getNbrCoffees();
    }
}
