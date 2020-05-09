package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Order {
    private static int nbrOrders = 1;
    private Integer orderNumber;
    private GregorianCalendar date;
    private Boolean toTakeAway;
    private Customer beneficiary;
    private Employee orderPicker;

    private ArrayList<FoodOrdering> foodOrderings;
    private ArrayList<DrinkOrdering> drinkOrderings;

    public Order(GregorianCalendar date, Boolean toTakeAway,
                 Customer beneficiary, Employee orderPicker) {
        orderNumber = nbrOrders;
        nbrOrders++;
        this.date = date;
        this.toTakeAway = toTakeAway;

        this.beneficiary = beneficiary;
        if(beneficiary != null)
            beneficiary.addOrder(this);

        this.orderPicker = orderPicker;

        foodOrderings = new ArrayList<>();
        drinkOrderings = new ArrayList<>();
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

    public void removeDrinkOrdering(DrinkOrdering drinkOrdering) {
        drinkOrderings.remove(drinkOrdering);
    }

    public static int getNbrOrders() {
        return nbrOrders;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public Boolean isToTakeAway() {
        return toTakeAway;
    }

    public Customer getBeneficiary() {
        return beneficiary;
    }

    public Employee getOrderPicker() {
        return orderPicker;
    }

    public ArrayList<FoodOrdering> getFoodOrderings() {
        return foodOrderings;
    }

    public ArrayList<DrinkOrdering> getDrinkOrderings() {
        return drinkOrderings;
    }
}
