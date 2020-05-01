package model;

public class Supplement {
    private Topping topping;
    private Drink drink;

    public Supplement(Topping topping, Drink drink) {
        this.topping = topping;
        this.drink = drink;
        drink.addSupplement(this);
    }

    @Override
    public String toString() {
        return topping.toString();
    }
}
