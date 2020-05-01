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

    public Customer(String password, String lastName, String firstName, String secondName,
                    String maidenName, GregorianCalendar birthDate, String streetName, Locality locality,
                    String email, String phone, Character gender)
            throws DateException, StringInputException, CharacterInputException {
        this(password, lastName, firstName, secondName, maidenName, birthDate, streetName, locality, email, phone, gender, Boolean.FALSE);
    }
  
    public Customer(String password, String lastName, String firstName, GregorianCalendar birthDateJava, String streetName, Locality locality, String email, String phone, char gender, boolean wantsAdvertising) throws StringInputException, DateException, CharacterInputException {
        super(password, lastName, firstName, birthDateJava, streetName, locality, email, phone, gender);
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

    public ArrayList<Order> getOrders() {
        return orders;
    }
  
    public void setSatisfactionDegree(Integer satisfactionDegree) throws IntegerInputException {
        if (satisfactionDegree < 0 || satisfactionDegree > 5)
            throw new IntegerInputException(satisfactionDegree, "degré satisfaction", "Le degré de satisfaction doit être compris entre 0 et 5 !");
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
