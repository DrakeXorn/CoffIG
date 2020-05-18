package controller;

import business.FoodManager;
import model.Food;
import model.exceptions.*;

import java.util.ArrayList;

public class FoodController {
    private FoodManager manager;

    public FoodController() {
        manager = new FoodManager();
    }

    public ArrayList<Food> getAllAvailableFoods() throws ConnectionException, DoubleInputException, DateException, IntegerInputException, AllDataException {
        return manager.getAllAvailableFoods();
    }
}
