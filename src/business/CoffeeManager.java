package business;

import dataAccess.CoffeeDBAccess;
import dataAccess.CoffeeDataAccess;
import model.Coffee;
import model.exceptions.*;

import java.util.ArrayList;

public class CoffeeManager {
    private CoffeeDataAccess dataAccessor;

    public CoffeeManager() {
        dataAccessor = new CoffeeDBAccess();
    }

    public ArrayList<Coffee> getAllCoffees() throws AllDataException, ConnectionException, DoubleInputException, IntegerInputException, DateException {
        return dataAccessor.getAllCoffees();
    }

    public void addCoffee(Coffee coffee) throws ConnectionException, AddDataException {
        dataAccessor.addCoffee(coffee);
    }

    public int getNbrCoffees() throws ConnectionException, AddDataException {
        return dataAccessor.getLastId();
    }

    public void updateCoffee(Coffee coffee) throws ConnectionException, AddDataException {
        dataAccessor.updateCoffee(coffee);
    }
}
