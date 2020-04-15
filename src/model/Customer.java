package model;

import model.exceptions.SatisfactionDegreeException;

import java.util.Date;
import java.util.GregorianCalendar;

public class Customer extends User {
    private Boolean wantAdvertising;
    private Integer satisfactionDegree;
    private LoyaltyCard loyaltyCard;

    // Constructors
    public Customer(Integer userID, String password, String lastName, String firstName, String secondName,
                    String maidenName, GregorianCalendar birthDate, String streetName, String email, String phone, Character gender,
                    Locality locality, Boolean wantAdvertising, Integer satisfactionDegree, LoyaltyCard loyaltyCard)
            throws SatisfactionDegreeException {
        super(userID, password, lastName, firstName, secondName, maidenName,
                birthDate, streetName, email, phone, gender, locality);

        this.wantAdvertising = wantAdvertising;
        setSatisfactionDegree(satisfactionDegree);
        this.loyaltyCard = loyaltyCard;
    }

    public Customer(Integer userID, String password, String lastName, String firstName, String secondName,
                    String maidenName, GregorianCalendar birthDate, String streetName, String email, String phone, Character gender,
                    Locality locality, Integer satisfactionDegree, LoyaltyCard loyaltyCard)
            throws SatisfactionDegreeException {
        this(userID, password, lastName, firstName, secondName, maidenName,
                birthDate, streetName, email, phone, gender, locality,
                Boolean.FALSE, satisfactionDegree, loyaltyCard);
    }

    public Customer(Integer userID, String password, String lastName, String firstName, String secondName,
                    String maidenName, GregorianCalendar birthDate, String streetName, String email, String phone, Character gender,
                    Locality locality, LoyaltyCard loyaltyCard)
            throws SatisfactionDegreeException {
        this(userID, password, lastName, firstName, secondName, maidenName,
                birthDate, streetName, email, phone, gender, locality,
                Boolean.FALSE, null, loyaltyCard);
    }

    public Customer(Integer userID, String password, String lastName, String firstName, String secondName,
                    String maidenName, GregorianCalendar birthDate, String streetName, String email, String phone, Character gender, Locality locality)
            throws SatisfactionDegreeException {
        this(userID, password, lastName, firstName, secondName, maidenName,
                birthDate, streetName, email, phone, gender, locality,
                false, null, null);
    }

    // Getters / Setters
    public Boolean getWantAdvertising() { return wantAdvertising; }
    public Integer getSatisfactionDegree() { return satisfactionDegree; }
    public LoyaltyCard getLoyaltyCard() { return loyaltyCard; }

    public void setSatisfactionDegree(Integer satisfactionDegree)
            throws SatisfactionDegreeException {
        if (satisfactionDegree < 0 || satisfactionDegree > 5)
            throw new SatisfactionDegreeException(satisfactionDegree);
        else this.satisfactionDegree = satisfactionDegree;
    }

    // Methods
    public String toString() {
        return super.toString() + ", " + wantAdvertising + ", " + satisfactionDegree;
    }
}