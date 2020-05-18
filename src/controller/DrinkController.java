package controller;

import business.DrinkManager;
import model.Drink;
import model.exceptions.*;

import java.util.ArrayList;

public class DrinkController {
    private DrinkManager manager;

    public DrinkController() {
        manager = new DrinkManager();
    }

    public ArrayList<Drink> getAllDrinks() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        return manager.getAllDrinks();
    }
}
