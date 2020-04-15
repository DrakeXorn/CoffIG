package model;

import model.exceptions.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoyaltyCard {
    private String loyaltyCardID;
    private Date registrationDate;
    private Integer pointsNumber;
    private ArrayList<Advantage> advantages = new ArrayList<>();

    private Customer customer; // Pas sur de +ºa

    // Constructor
    public LoyaltyCard(Integer loyaltyCardID, Date registrationDate, Integer pointsNumber, Customer customer)
            throws PointsRequiredException, PointsNumberException {
        this.loyaltyCardID = customer.getPhone(); // Pas sur de +ºa
        this.registrationDate = registrationDate;
        setPointsNumber(pointsNumber);
        this.customer = customer;
    }

    public LoyaltyCard(Integer loyaltyCardID, Date registrationDate, Customer customer)
            throws PointsRequiredException, PointsNumberException {
        this(loyaltyCardID, registrationDate, 50, customer); // Si les points ne sont pas pr+®cis+® pour la cr+®ation
    }

    // Getters / Setters
    public String getLoyaltyCardID() { return loyaltyCardID; }
    public Date getRegistrationDate() { return registrationDate; }
    public Integer getPointsNumber() { return pointsNumber; }

    public void setPointsNumber(Integer pointsNumber)
            throws PointsNumberException {
        if (pointsNumber < 0) throw new PointsNumberException(pointsNumber);
        else this.pointsNumber = pointsNumber;
    }

    // Methods
    public void addAdvantage(Advantage newAdvantage) { advantages.add(newAdvantage); }
    public void addAdvantages(Advantage ...newAdvantages) {
        for (Advantage advantage : newAdvantages)
            addAdvantage(advantage);
    }

    public void removeAdvantage(Advantage newAdvantage) { advantages.remove(newAdvantage); }
    public void removeAdvantages(Advantage ...newAdvantages) {
        for (Advantage advantage :  newAdvantages)
            removeAdvantage(advantage);
    }

    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // Pas sur de +º+á
        return loyaltyCardID +", "+dateFormat.format(registrationDate)+", "+pointsNumber;
    }
}