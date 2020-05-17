package business;

import dataAccess.FoodDBAccess;
import dataAccess.FoodDataAccess;
import model.Food;
import model.exceptions.*;

import java.util.ArrayList;

public class FoodManager {
    private FoodDataAccess dataAccessor;

    public FoodManager() {
        dataAccessor = new FoodDBAccess();
    }

    public ArrayList<Food> getAllAvailableFoods() throws ConnectionException, DoubleInputException, DateException, IntegerInputException, AllDataException {
        return dataAccessor.getAllAvailableFoods();
    }
}
