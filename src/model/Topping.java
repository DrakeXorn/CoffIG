package model;

import model.exceptions.DoubleInputException;

public class Topping {
    private static int nbrToppings = 1;
    private Integer toppingID;
    private String label;
    private Double price;
    private StockLocation stock;

    public Topping(Integer toppingID, String label, Double price, StockLocation stock) throws DoubleInputException {
        this.toppingID = toppingID;
        this.label = label;
        setPrice(price);
        this.stock = stock;
    }
    
    // pour la recherche des anciennes commandes
    public Topping(Integer toppingID, String label, Double price) throws DoubleInputException {
        this.toppingID = toppingID;
        if (toppingID > nbrToppings)
            nbrToppings = toppingID + 1;
        this.label = label;
        setPrice(price);
    }

    public Integer getToppingID() {
        return toppingID;
    }

    public String getLabel() {
        return label;
    }

    public Double getPrice() {
        return price;
    }

    public StockLocation getStockLocation() {
        return stock;
    }

    public void setPrice(Double price) throws DoubleInputException {
        if (price < 0)
            throw new DoubleInputException(price, "le prix", "Le prix doit être positif et différent de 0 !");
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Topping && ((Topping) o).getToppingID().equals(toppingID) && ((Topping) o).getLabel().equals(label);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String toString() {

        return label + " (" + price + " €)";
    }
}
