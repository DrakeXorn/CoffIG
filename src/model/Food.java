package model;

import model.exceptions.DoubleInputException;

public class Food {
    private static int nbrFoods = 1;
    private Integer foodID;
    private String label;
    private Double price;
    private StockLocation stockLocation;

    public Food(String label, Double price, StockLocation stockLocation)
            throws DoubleInputException {
        foodID = nbrFoods;
        nbrFoods++;
        this.label = label;
        setPrice(price);
        this.stockLocation = stockLocation;
    }

    public Food(Integer foodID, String label, Double price, StockLocation stockLocation) throws DoubleInputException {
        this.foodID = foodID;
        if (foodID > nbrFoods)
            nbrFoods = foodID;
        this.label = label;
        setPrice(price);
        this.stockLocation = stockLocation;
    }

    public void setPrice(Double price) throws DoubleInputException {
        if (price < 0)
            throw new DoubleInputException(price, "le prix", "Le prix doit être positif et différent de 0 !");
        this.price = price;
    }

    public String toString() {
        return foodID + ", " + label + ", " + price;
    }
}
