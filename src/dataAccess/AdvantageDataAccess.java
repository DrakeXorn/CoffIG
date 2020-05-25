package dataAccess;

import model.Advantage;
import model.Customer;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface AdvantageDataAccess {
    ArrayList<Double> getAllAdvantageDiscounts() throws ConnectionException, AllDataException;
    ArrayList<Advantage> searchAdvantages(Customer customer, GregorianCalendar date, Double discount, int typAdvantage) throws AllDataException, ConnectionException, DateException, IntegerInputException, DoubleInputException;
}

