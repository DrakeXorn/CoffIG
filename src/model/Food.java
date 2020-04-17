package model;

import model.exceptions.PriceException;

public class Food {
    private static int nbrFoods = 1;
    private Integer foodID;
    private String label;
    private Double price;
    private StockLocation stockLocation;

    public Food(String label, Double price, StockLocation stockLocation)
            throws PriceException {
        foodID = nbrFoods;
        nbrFoods++;
        this.label = label;
        setPrice(price);
        this.stockLocation = stockLocation;
    }

    public Food(Integer foodID, String label, Double price, StockLocation stockLocation) throws PriceException {
        this.foodID = foodID;
        if (foodID > nbrFoods)
            nbrFoods = foodID;
        this.label = label;
        setPrice(price);
        this.stockLocation = stockLocation;
    }

    public void setPrice(Double price) throws PriceException {
        if (price < 0)
            throw new PriceException(price);
        this.price = price;
    }

    public String toString() {
        return foodID + ", " + label + ", " + price;
    }
}
