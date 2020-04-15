package model;

import java.util.GregorianCalendar;

public class Order {
    private static int orderNumber;
    private GregorianCalendar date;
    private Boolean isToTakeAway;
    private Customer beneficiary;
    private Employee orderPicker;

    private FoodOrdering [] foodOrderings;
    private int nbFoodOrderings;
    private final static int NB_FOOD_ORDDERINGS = 20;

    private DrinkOrdering [] drinkOrderings;
    private int nbDrinkOrderings;
    private final static int NB_DRINK_ORDDERINGS = 20;

    public Order(GregorianCalendar date, Boolean isToTakeAway,
                 Customer beneficiary, Employee orderPicker) {
        orderNumber++;
        this.date = date;
        this.isToTakeAway = isToTakeAway;

        this.beneficiary = beneficiary;
        if(beneficiary != null)
            beneficiary.addOrder(this);

        this.orderPicker = orderPicker;
        if(orderPicker != null)
            orderPicker.addOrder(this);

        foodOrderings = new FoodOrdering[NB_FOOD_ORDDERINGS];
        drinkOrderings = new DrinkOrdering[NB_DRINK_ORDDERINGS];
    }

    // à appeler dans le constructeur de FoodOrdering sur Order
    public void addFoodOrdering(FoodOrdering foodOrdering){
        if(nbFoodOrderings < NB_FOOD_ORDDERINGS){
            foodOrderings[nbFoodOrderings] = foodOrdering;
            nbFoodOrderings++;
        }
    }

    // à appeler dans le constructeur de DrinkOrdering sur Order
    public void addDrinkOrdering(DrinkOrdering drinkOrdering){
        if(nbDrinkOrderings < NB_DRINK_ORDDERINGS){
            drinkOrderings[nbDrinkOrderings] = drinkOrdering;
            nbDrinkOrderings++;
        }
    }
}
