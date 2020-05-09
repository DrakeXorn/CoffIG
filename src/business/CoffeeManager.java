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

    public ArrayList<Coffee> getAllCoffees() throws AllDataException, ConnectionException, DoubleInputException, IntegerInputException {
        return dataAccessor.getAllCoffees();
    }

    public boolean addCoffee(Coffee coffee) throws ConnectionException, AddDataException {
        return dataAccessor.addCoffee(coffee);
    }

    public int getNbrCoffees() throws ConnectionException, AddDataException {
        return dataAccessor.getLastId();
    }
}
