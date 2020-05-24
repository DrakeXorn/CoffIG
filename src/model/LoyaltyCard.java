package model;

import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class LoyaltyCard {
    private String loyaltyCardID;
    private GregorianCalendar registrationDate;
    private Integer pointsNumber;
    private ArrayList<Advantage> advantages;

    public LoyaltyCard(GregorianCalendar registrationDate, Integer pointsNumber, Customer customer)
            throws IntegerInputException {
        if(customer != null)
            this.loyaltyCardID = customer.getPhone();

        this.registrationDate = registrationDate;
        setPointsNumber(pointsNumber);
        advantages = new ArrayList<>();
    }

    // pour la création d'une carte en créant un client
    public LoyaltyCard(GregorianCalendar registrationDate, Customer customer)
            throws IntegerInputException {
        this(registrationDate, 500, customer);
    }

    // constructeur pour récupérer AllCustomers dans DBAccess
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
}
