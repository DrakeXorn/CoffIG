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

    public ArrayList<Double> getAllAdvantageDiscount() throws ConnectionException, AllDataException {
        return manager.getAllAdvantageDiscount();
    }

    public ArrayList<Advantage> searchAdvantages(Customer customer, GregorianCalendar date, Double discount, int typeAdvantage) throws AllDataException, ConnectionException, DateException, IntegerInputException, DoubleInputException, BusinessException {
        return manager.searchAdvantages(customer, date, discount, typeAdvantage);
    }
}
