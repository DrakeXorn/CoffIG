package dataAccess;

import model.Topping;
import model.exceptions.*;

import java.util.ArrayList;

public interface ToppingDataAccess {
    ArrayList<Topping> getAllAvailableToppings() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException;
}
