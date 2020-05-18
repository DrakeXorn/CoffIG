package business;

import dataAccess.AdvantageDBAccess;
import model.Advantage;
import model.Customer;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AdvantageManager {
    private AdvantageDBAccess dataAccessor;

    public AdvantageManager() { dataAccessor = new AdvantageDBAccess(); }

    public void getAllAdvantageDiscount() throws ConnectionException, AddDataException {
        dataAccessor.getAllAdvantageDiscount();
    }

    public ArrayList<Double> getDiscounts() {
        return dataAccessor.getDiscounts();
    }

    public ArrayList<Advantage> searchAdvantages(Customer customer, GregorianCalendar today, Double discount, int typAdvantage) throws AllDataException, ConnectionException, DateException, IntegerInputException, DoubleInputException {
        return dataAccessor.searchAdvantages(customer, today, discount, typAdvantage);
    }
}
