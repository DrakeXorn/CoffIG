package model;

import model.exceptions.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LoyaltyCard {
    private String loyaltyCardID;
    private GregorianCalendar registrationDate;
    private Integer pointsNumber;
    private ArrayList<Advantage> advantages;
    private Customer customer;

    public LoyaltyCard(GregorianCalendar registrationDate, Integer pointsNumber, Customer customer)
            throws IntegerInputException {
        this.customer = customer;
        if(customer != null)
            this.loyaltyCardID = customer.getPhone();

        this.registrationDate = registrationDate;
        setPointsNumber(pointsNumber);
        advantages = new ArrayList<>();
    }

    public LoyaltyCard(GregorianCalendar registrationDate, Customer customer)
            throws IntegerInputException {
        this(registrationDate, 100, customer);
    }

    // constructeur pour récupérer AllCustormer dans DBAccess
    public LoyaltyCard(String loyaltyCardID, GregorianCalendar registrationDate, Integer pointsNumber)
            throws IntegerInputException {
        this.loyaltyCardID = loyaltyCardID;
        this.registrationDate = registrationDate;
        setPointsNumber(pointsNumber);
        advantages = new ArrayList<>();
    }

    public String getLoyaltyCardID() {
        return loyaltyCardID;
    }

    public GregorianCalendar getRegistrationDate() {
        return registrationDate;
    }

    public Integer getPointsNumber() {
        return pointsNumber;
    }

    public void setPointsNumber(Integer pointsNumber) throws IntegerInputException {
        if (pointsNumber < 0) throw new IntegerInputException(pointsNumber, "le nombre de points", "Le nombre de points doit être positif");
        else this.pointsNumber = pointsNumber;
    }

    public void addAdvantages(ArrayList<Advantage> newRights) {
        advantages.addAll(newRights);
    }

    public void removeAdvantages(ArrayList<Advantage> rightsToDelete) {
        advantages.removeAll(rightsToDelete);
    }

    public String toString() {
        // TODO: à modifier
        return "Carte de fidélité numéro : " + loyaltyCardID + " créée le "
                + registrationDate.get(Calendar.DAY_OF_MONTH)
                + "/" + (registrationDate.get(Calendar.MONTH) + 1) +
                "/" + registrationDate.get(Calendar.YEAR) +
                " avec " + pointsNumber + " points de fidélité";
    }
}
