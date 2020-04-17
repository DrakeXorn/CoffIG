package model;

import model.exceptions.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class LoyaltyCard {
    private String loyaltyCardID;
    private GregorianCalendar registrationDate;
    private Integer pointsNumber;
    private ArrayList<Right> rights;
    private Customer customer;

    public LoyaltyCard(GregorianCalendar registrationDate, Integer pointsNumber, Customer customer)
            throws PointsNumberException {
        this.loyaltyCardID = customer.getPhone();
        this.registrationDate = registrationDate;
        setPointsNumber(pointsNumber);
        this.customer = customer;
        customer.addLoyaltyCard(this);
        rights = new ArrayList<>();
    }

    public LoyaltyCard(GregorianCalendar registrationDate, Customer customer)
            throws PointsNumberException {
        this(registrationDate, 100, customer);
    }

    public void setPointsNumber(Integer pointsNumber) throws PointsNumberException {
        if (pointsNumber < 0) throw new PointsNumberException(pointsNumber);
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // Pas sur de +º+á
        return loyaltyCardID + ", " + dateFormat.format(registrationDate) + ", " + pointsNumber;
    }
}