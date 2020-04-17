package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Order {
    private static int nbrOrders = 1;
    private Integer orderNumber;
    private GregorianCalendar date;
    private Boolean isToTakeAway;
    private Customer beneficiary;
    private Employee orderPicker;

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

    public void addFoodOrdering(FoodOrdering foodOrdering) {
        foodOrderings.add(foodOrdering);
    }

    public void removeFoodOrdering(FoodOrdering foodOrdering) {
        foodOrderings.remove(foodOrdering);
    }

    public void addDrinkOrdering(DrinkOrdering drinkOrdering) {
        drinkOrderings.add(drinkOrdering);
    }
}
