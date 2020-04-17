package model;

import model.exceptions.PriceException;

public class Topping {
    private static int nbrToppings = 1;
    private Integer toppingID;
    private String label;
    private Double price;
    private StockLocation stock;

    public Topping(String label, Double price, StockLocation stock) throws PriceException {
        toppingID = nbrToppings;
        nbrToppings++;
        this.label = label;
        setPrice(price);
        this.stock = stock;
    }

    public Topping(Integer toppingID, String label, Double price, StockLocation stock) throws PriceException {
        this.toppingID = toppingID;
        if (toppingID > nbrToppings)
            nbrToppings = toppingID + 1;
        this.label = label;
        setPrice(price);
        this.stock = stock;
    }

    public void setPrice(Double price) throws PriceException {
        if (price < 0)
            throw new PriceException(price);
        this.price = price;
    }

    public String toString() {
        StringBuilder res = new StringBuilder(label);

        res.append(" (").append(price).append(" €).");

        return res.toString();
    }
}
