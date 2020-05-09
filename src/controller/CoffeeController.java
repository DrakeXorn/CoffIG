package controller;

import business.CoffeeManager;
import model.Coffee;
import model.exceptions.AddCoffeeException;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public class CoffeeController {
    private CoffeeManager coffeeManager;

    public CoffeeController() {
        coffeeManager = new CoffeeManager();
    }

    public ArrayList<Coffee> getAllCoffees() throws AllDataException, ConnectionException {
        return coffeeManager.getAllCoffees();
    }

    public boolean addCoffee(Coffee coffee) throws ConnectionException, AddCoffeeException {
        return coffeeManager.addCoffee(coffee);
    }

    public int getNbrCoffees() throws ConnectionException, AddCoffeeException {
        return coffeeManager.getNbrCoffees();
    }
}
