package dataAccess;

import model.Drink;
import model.exceptions.*;

import java.util.ArrayList;

public interface DrinkDataAccess {
    ArrayList<Drink> getAllDrinks() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException;
}
