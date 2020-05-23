package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Order {
    private Integer orderNumber;
    private GregorianCalendar date;
    private Boolean toTakeAway;
    private Customer beneficiary;
    private Employee orderPicker;
    private Double discount;
    private ArrayList<FoodOrdering> foodOrderings;
    private ArrayList<DrinkOrdering> drinkOrderings;

    // pour la recherche des anciennes commandes
    public Order(Integer orderNumber, GregorianCalendar date, Boolean isToTakeAway) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.toTakeAway = isToTakeAway;

        foodOrderings = new ArrayList<>();
        drinkOrderings = new ArrayList<>();
    }

    public double getPrice() {
        double price = 0;

        for (FoodOrdering foodOrdering : foodOrderings)
            price += foodOrdering.getPrice();

        for (DrinkOrdering drinkOrdering : drinkOrderings)
            price += drinkOrdering.getPrice();

        return discount != null ? price - discount : price;
    }

    public void setDiscount(Double discount){
        this.discount = discount;
    }

    public void setBeneficiary(Customer beneficiary) {
        this.beneficiary = beneficiary;
    }

    public void setOrderPicker(Employee orderPicker) {
        this.orderPicker = orderPicker;
    }

    public void setFoodOrderings(ArrayList<FoodOrdering> foodOrderings) {
        this.foodOrderings = foodOrderings;
    }

    public void setDrinkOrderings(ArrayList<DrinkOrdering> drinkOrderings) {
        this.drinkOrderings = drinkOrderings;
    }

    public void addFoodOrdering(FoodOrdering foodOrdering) {
        foodOrderings.add(foodOrdering);
    }

    public void addDrinkOrdering(DrinkOrdering drinkOrdering) {
        drinkOrderings.add(drinkOrdering);
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

    public String toString (){
        return "Commande " + orderNumber +
                (toTakeAway ? " à emporter" : " sur place") +
                " passée le " + date.get(Calendar.DAY_OF_MONTH)
                + "/" + (date.get(Calendar.MONTH ) + 1) +
                "/" + date.get(Calendar.YEAR);
    }
}
