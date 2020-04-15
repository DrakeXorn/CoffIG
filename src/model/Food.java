package model;

import model.exceptions.FoodIDException;
import model.exceptions.PriceException;

public class Food {
    private Integer foodID;
    private String label;
    private Double price;
    private StockLocation stockLocation;

    // Constructor
    public Food(Integer foodID, String label, Double price, StockLocation stockLocation)
            throws FoodIDException, PriceException
    {
        setFoodID(foodID);
        this.label = label;
        setPrice(price);
        this.stockLocation = stockLocation;
    }


    // Getters / Setters
    public Integer getFoodID() { return foodID; }
    public String getLabel() { return label; }
    public Double getPrice() { return price; }

    public void setFoodID(Integer foodID) throws FoodIDException {
        if (foodID < 0 || foodID > 100) throw new FoodIDException(foodID);
        else this.foodID = foodID;
    }
    public void setLabel(String label) { this.label = label; }

    public void setPrice(Double price) throws PriceException {
        if (price < 0) throw new PriceException(price);
        else this.price = price;
    }
    public void setStockLocation(StockLocation stockLocation) { this.stockLocation = stockLocation; }



    // Methods
    public String toString() {
        return foodID+", "+label+", "+price;
    }
}
