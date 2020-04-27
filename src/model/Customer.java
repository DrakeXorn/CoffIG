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
            throws DateException, StringInputException, CharacterInputException {
        super(password, lastName, firstName, secondName, maidenName,
                birthDate, streetName, locality, email, phone, gender);

        this.wantsAdvertising = wantsAdvertising;
        orders = new ArrayList<>();
    }

    public void setSatisfactionDegree(Integer satisfactionDegree) throws IntegerInputException {
        if (satisfactionDegree < 0 || satisfactionDegree > 5)
            throw new IntegerInputException(satisfactionDegree, "degré satisfaction", "Le degré de satisfaction doit être compris entre 0 et 5 !");
        this.satisfactionDegree = satisfactionDegree;
    }

    public Boolean getWantsAdvertising() {
        return wantsAdvertising;
    }

    public Integer getSatisfactionDegree() {
        return satisfactionDegree;
    }

    public LoyaltyCard getLoyaltyCard() {
        return loyaltyCard;
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
