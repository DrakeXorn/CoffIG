package business;

import dataAccess.CoffeeDBAccess;
import dataAccess.CoffeeDataAccess;
import model.Coffee;
import model.exceptions.AddCoffeeException;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.util.ArrayList;

public class CoffeeManager {
    private CoffeeDataAccess dataAccessor;

    public CoffeeManager() {
        dataAccessor = new CoffeeDBAccess();
    }

    public ArrayList<Coffee> getAllCoffees() throws AllDataException, ConnectionException {
        return dataAccessor.getAllCoffees();
    }

    public boolean addCoffee(Coffee coffee) throws ConnectionException, AddCoffeeException {
        return dataAccessor.addCoffee(coffee);
    }

    public int getNbrCoffees() throws ConnectionException, AddCoffeeException {
        return dataAccessor.getNbrCoffees();
    }
}
