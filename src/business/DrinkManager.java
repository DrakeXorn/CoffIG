package business;

import dataAccess.DrinkDBAccess;
import dataAccess.DrinkDataAccess;
import model.Drink;
import model.exceptions.*;

import java.util.ArrayList;

public class DrinkManager {
    private DrinkDataAccess accessor;

    public DrinkManager() {
        accessor = new DrinkDBAccess();
    }

    public ArrayList<Drink> getAllDrinks() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        return accessor.getAllDrinks();
    }
}
