package dataAccess;

import model.Advantage;
import model.Customer;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface AdvantageDataAccess {
    void getAllAdvantageDiscount() throws ConnectionException, AddDataException;

    ArrayList<Double> getDiscounts();

    ArrayList<Advantage> searchAdvantages(Customer customer, GregorianCalendar today, Double discount, int typAdvantage) throws AllDataException, ConnectionException, DateException, IntegerInputException, DoubleInputException;
}

