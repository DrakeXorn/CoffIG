package model;

import model.exceptions.DoubleInputException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Order {
    private static int nbrOrders = 1;
    private Integer orderNumber;
    private GregorianCalendar date;
    private Boolean isToTakeAway;
    private Customer beneficiary;
    private Employee orderPicker;
    private double price;

    private ArrayList<FoodOrdering> foodOrderings;
    private ArrayList<DrinkOrdering> drinkOrderings;

    public Order(GregorianCalendar date, Boolean isToTakeAway,
                 Customer beneficiary, Employee orderPicker) {
        orderNumber = nbrOrders;
        nbrOrders++;
        this.date = date;
        this.isToTakeAway = isToTakeAway;

        this.beneficiary = beneficiary;
        if(beneficiary != null)
            beneficiary.addOrder(this);

        this.orderPicker = orderPicker;

        foodOrderings = new ArrayList<>();
        drinkOrderings = new ArrayList<>();
    }

    // pour la recherche des anciennes commandes
    public Order(Integer orderNumber, GregorianCalendar date, Boolean isToTakeAway) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.isToTakeAway = isToTakeAway;
        this.price = 0;

        foodOrderings = new ArrayList<>();
        drinkOrderings = new ArrayList<>();
    }

    public ArrayList<FoodOrdering> getFoodOrderings() {
        return foodOrderings;
    }

    public ArrayList<DrinkOrdering> getDrinkOrderings() {
        return drinkOrderings;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public Boolean getToTakeAway() {
        return isToTakeAway;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws DoubleInputException {
        if(price < 0)
            throw new DoubleInputException(price, "le prix", "Le prix doit être positif et différent de 0 !");
        this.price += price;
    }

    public void setBeneficiary(Customer beneficiary) {
        this.beneficiary = beneficiary;
    }

    public void setOrderPicker(Employee orderPicker) {
        this.orderPicker = orderPicker;
    }

    public void addFoodOrdering(FoodOrdering foodOrdering) {
        foodOrderings.add(foodOrdering);
    }

    public void removeFoodOrdering(FoodOrdering foodOrdering) {
        foodOrderings.remove(foodOrdering);
    }

    public void addDrinkOrdering(DrinkOrdering drinkOrdering) {
        drinkOrderings.add(drinkOrdering);
    }

    public String toString (){
        return "Commande " + orderNumber +
                (isToTakeAway ? " à emporter" : " sur place") +
                " passée le " + date.get(Calendar.DAY_OF_MONTH)
                + "/" + (date.get(Calendar.MONTH ) + 1) +
                "/" + date.get(Calendar.YEAR);

    }
}
