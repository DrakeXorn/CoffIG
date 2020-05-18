package dataAccess;

import model.Drink;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public interface DrinkDataAccess {
    ArrayList<Drink> getAllDrinks() throws ConnectionException, AllDataException;
}
