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

    public ArrayList<Advantage> searchAdvantages(Customer customer, GregorianCalendar date, Double discount, int typeAdvantage) throws AllDataException, ConnectionException, DateException, IntegerInputException, DoubleInputException, BusinessException {
        if (customer == null)
            throw new BusinessException("AdvantageManager", "la recherche d'avantages", "Le client");
        if (date == null)
            throw new BusinessException("AdvantageManager", "la recherche d'avantages", "La date");
        return dataAccessor.searchAdvantages(customer, date, discount, typeAdvantage);
    }
}
