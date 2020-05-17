package controller;

import business.DrinkManager;
import model.Drink;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public class DrinkController {
    private DrinkManager manager;

    public DrinkController() {
        manager = new DrinkManager();
    }

    public ArrayList<Drink> getAllDrinks() throws ConnectionException, AllDataException {
        return manager.getAllDrinks();
    }
}
