package model;

import model.exceptions.GenderException;
import model.exceptions.SatisfactionDegreeException;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Customer extends User {
    private Boolean wantsAdvertising;
    private Integer satisfactionDegree;
    private LoyaltyCard loyaltyCard;
    private ArrayList<Order> orders;

    public Customer(String password, String lastName, String firstName, String secondName,
                    String maidenName, GregorianCalendar birthDate, String streetName, String email, String phone, Character gender,
                    Locality locality, Boolean wantsAdvertising) throws GenderException {
        super(password, lastName, firstName, secondName, maidenName,
                birthDate, streetName, locality, email, phone, gender);

        this.wantsAdvertising = wantsAdvertising;
        orders = new ArrayList<>();
    }

    public Customer(String password, String lastName, String firstName, String secondName,
                    String maidenName, GregorianCalendar birthDate, String streetName, String email, String phone,
                    Character gender, Locality locality) throws GenderException {
        this(password, lastName, firstName, secondName, maidenName, birthDate, streetName, email, phone, gender,
                locality, Boolean.FALSE);
    }

    public void setSatisfactionDegree(Integer satisfactionDegree)
            throws SatisfactionDegreeException {
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
                " recevoir la newsletter et a un degré de satisfaction de  " + satisfactionDegree;
    }
}