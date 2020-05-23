package business;

import dataAccess.AdvantageDBAccess;
import dataAccess.AdvantageDataAccess;
import model.Advantage;
import model.Customer;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AdvantageManager {
    private AdvantageDataAccess dataAccessor;

    public AdvantageManager() {
        dataAccessor = new AdvantageDBAccess();
    }

    public ArrayList<Double> getAllAdvantageDiscount() throws ConnectionException, AllDataException {
        return dataAccessor.getAllAdvantageDiscounts();
    }

    public ArrayList<Advantage> searchAdvantages(Customer customer, GregorianCalendar today, Double discount, int typeAdvantage) throws AllDataException, ConnectionException, DateException, IntegerInputException, DoubleInputException {
        return dataAccessor.searchAdvantages(customer, today, discount, typeAdvantage);
    }
}
