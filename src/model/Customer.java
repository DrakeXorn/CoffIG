package model;

import model.exceptions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Customer extends User {
    private Boolean wantsAdvertising;
    private Integer satisfactionDegree;
    private LoyaltyCard loyaltyCard;
    private ArrayList<Order> orders;

    public Customer(String password, String lastName, String firstName, String secondName,
                    String maidenName, GregorianCalendar birthDate, String streetName, Locality locality,
                    String email, String phone, Character gender, Boolean wantsAdvertising)
            throws StreetException, EmailException, PasswordException, GenderException, PhoneException, NameException, FirstNameException, DateException {
        super(password, lastName, firstName, secondName, maidenName,
                birthDate, streetName, locality, email, phone, gender);

        this.wantsAdvertising = wantsAdvertising;
        orders = new ArrayList<>();
    }

    public Customer(String password, String lastName, String firstName, String secondName,
                    String maidenName, GregorianCalendar birthDate, String streetName, Locality locality,
                    String email, String phone, Character gender)
            throws EmailException, PasswordException, GenderException, NameException, PhoneException, StreetException, FirstNameException, DateException {
        this(password, lastName, firstName, secondName, maidenName, birthDate, streetName, locality, email, phone, gender, Boolean.FALSE);
    }

    public void setSatisfactionDegree(Integer satisfactionDegree) throws SatisfactionDegreeException {
        if (satisfactionDegree < 0 || satisfactionDegree > 5)
            throw new SatisfactionDegreeException(satisfactionDegree);
        this.satisfactionDegree = satisfactionDegree;
    }

    public void addLoyaltyCard(LoyaltyCard card) {
        loyaltyCard = card;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addOrders(ArrayList<Order> newOrders) {
        orders.addAll(newOrders);
    }

    public String toString() {
        return super.toString() + ", " +
                (wantsAdvertising ? " souhaite" : " ne souhaite pas") +
                " recevoir la newsletter" +
                (satisfactionDegree != null ? " et a un degré de satisfaction de  " + satisfactionDegree : "");
    }
}