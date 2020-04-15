package model;

public class Topping {
    private static Integer nbrToppings;
    private Integer toppingID;
    private String label;
    private Double price;
    private StockLocation stock;

    public Topping(String label, Double price, StockLocation stock) {
        toppingID = nbrToppings;
        nbrToppings++;
        this.label = label;
        this.price = price;
        this.stock = stock;
    }

    public String toString() {
        StringBuilder res = new StringBuilder(label);

        res.append(" (").append(price).append(" €).");

        return res.toString();
    }
}
