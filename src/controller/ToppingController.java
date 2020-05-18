package controller;

import business.ToppingManager;
import model.Topping;
import model.exceptions.*;

import java.util.ArrayList;

public class ToppingController {
    private ToppingManager manager;

    public ToppingController() {
        manager = new ToppingManager();
    }

    public ArrayList<Topping> getAllAvailableToppings() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        return manager.getAllAvailableToppings();
    }
}
