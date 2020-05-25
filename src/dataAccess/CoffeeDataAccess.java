package dataAccess;

import model.Coffee;
import model.exceptions.*;

import java.util.ArrayList;

public interface CoffeeDataAccess {
    ArrayList<Coffee> getAllCoffees() throws AllDataException, ConnectionException, DoubleInputException, IntegerInputException, DateException;
    void addCoffee(Coffee coffee) throws ConnectionException, AddDataException;
    void updateCoffee(Coffee coffee) throws ConnectionException, ModifyException;
    int getLastCoffeeID() throws ConnectionException, AddDataException;
    ArrayList<String> getFeatures() throws AllDataException, ConnectionException;
    }
