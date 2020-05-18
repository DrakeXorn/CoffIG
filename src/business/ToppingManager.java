package business;

import dataAccess.ToppingDBAccess;
import dataAccess.ToppingDataAccess;
import model.Topping;
import model.exceptions.*;

import java.util.ArrayList;

public class ToppingManager {
    private ToppingDataAccess accessor;

    public ToppingManager() {
        accessor = new ToppingDBAccess();
    }

    public ArrayList<Topping> getAllAvailableToppings() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        return accessor.getAllAvailableToppings();
    }
}
