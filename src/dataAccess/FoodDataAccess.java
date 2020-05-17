package dataAccess;

import model.Food;
import model.exceptions.*;

import java.util.ArrayList;

public interface FoodDataAccess {
    ArrayList<Food> getAllAvailableFoods() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException;
}
