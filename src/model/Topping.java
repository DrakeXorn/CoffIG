package model;

import model.exceptions.DoubleInputException;

public class Topping {
    private static int nbrToppings = 1;
    private Integer toppingID;
    private String label;
    private Double price;
    private StockLocation stock;

    public Topping(String label, Double price, StockLocation stock) throws DoubleInputException {
        toppingID = nbrToppings;
        nbrToppings++;
        this.label = label;
        setPrice(price);
        this.stock = stock;
    }

    public Topping(Integer toppingID, String label, Double price, StockLocation stock) throws DoubleInputException {
        this.toppingID = toppingID;
        if (toppingID > nbrToppings)
            nbrToppings = toppingID + 1;
        this.label = label;
        setPrice(price);
        this.stock = stock;
    }

    public void setPrice(Double price) throws DoubleInputException {
        if (price < 0)
            throw new DoubleInputException(price, "le prix", "Le prix doit être positif et différent de 0 !");
        this.price = price;
    }

    public String toString() {
        StringBuilder res = new StringBuilder(label);

        res.append(" (").append(price).append(" €).");

        return res.toString();
    }
}
