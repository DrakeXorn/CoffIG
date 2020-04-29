package model;

import model.exceptions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LoyaltyCard {
    private String loyaltyCardId;
    private GregorianCalendar registrationDate;
    private Integer pointsNumber;
    private ArrayList<Right> rights;
    private Customer customer;

    public LoyaltyCard(GregorianCalendar registrationDate, Integer pointsNumber, Customer customer)
            throws IntegerInputException {
        this.loyaltyCardId = customer.getPhone();
        this.registrationDate = registrationDate;
        setPointsNumber(pointsNumber);
        this.customer = customer;
        customer.addLoyaltyCard(this);
        rights = new ArrayList<>();
    }

    public LoyaltyCard(GregorianCalendar registrationDate, Customer customer)
            throws IntegerInputException {
        this(registrationDate, 100, customer);
    }

    public LoyaltyCard(String loyaltyCardId, GregorianCalendar registrationDateJava, Integer pointsNumber, Customer customer) throws IntegerInputException {
        this.loyaltyCardId = loyaltyCardId;
        registrationDate = registrationDateJava;
        setPointsNumber(pointsNumber);
        this.customer = customer;
    }

    public void setPointsNumber(Integer pointsNumber) throws IntegerInputException {
        if (pointsNumber < 0) throw new IntegerInputException(pointsNumber, "le nombre de points", "Le nombre de points doit être positif");
        else this.pointsNumber = pointsNumber;
    }

    public void addAdvantages(ArrayList<Right> newRights) {
        rights.addAll(newRights);
    }

    public void removeAdvantages(ArrayList<Right> rightsToDelete) {
        rights.removeAll(rightsToDelete);
    }

    public String toString() {
        // TODO: à modifier
        return "Carte de fidélité numéro : " + loyaltyCardId + " créée le "
                + registrationDate.get(Calendar.DAY_OF_MONTH)
                + "/" + (registrationDate.get(Calendar.MONTH) + 1) +
                "/" + registrationDate.get(Calendar.YEAR) +
                " avec " + pointsNumber + " points de fidélité";
    }
}
