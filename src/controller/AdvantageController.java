package controller;

import business.AdvantageManager;
import model.Advantage;
import model.Customer;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AdvantageController {
    private AdvantageManager manager;

    public AdvantageController() {
        manager = new AdvantageManager();
    }

    public void getAllAdvantageDiscount() throws ConnectionException, AddDataException {
        manager.getAllAdvantageDiscount();
    }

    public ArrayList<Double> getDiscounts() {
        return manager.getDiscounts();
    }

    public ArrayList<Advantage> searchAdvantages(Customer customer, GregorianCalendar today, Double discount, int typAdvantage) throws AllDataException, ConnectionException, DateException, IntegerInputException, DoubleInputException {
        return manager.searchAdvantages(customer, today, discount, typAdvantage);
    }
}
