package model;

import model.exceptions.DoubleInputException;

public class Food {
    private static int nbrFoods = 1;
    private Integer foodId;
    private String label;
    private Double price;
    private StockLocation stockLocation;

    public Food(String label, Double price, StockLocation stockLocation)
            throws DoubleInputException {
        foodId = nbrFoods;
        nbrFoods++;
        this.label = label;
        setPrice(price);
        this.stockLocation = stockLocation;
    }

    public Food(Integer foodId, String label, Double price, StockLocation stockLocation) throws DoubleInputException {
        this.foodId = foodId;
        if (foodId > nbrFoods)
            nbrFoods = foodId;
        this.label = label;
        setPrice(price);
        this.stockLocation = stockLocation;
    }

    public static int getNbrFoods() {
        return nbrFoods;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public String getLabel() {
        return label;
    }

    public Double getPrice() {
        return price;
    }

    public StockLocation getStockLocation() {
        return stockLocation;
    }

    public void setPrice(Double price) throws DoubleInputException {
        if (price < 0)
            throw new DoubleInputException(price, "le prix", "Le prix doit être positif et différent de 0 !");
        this.price = price;
    }

    public String toString() {
        return foodId + ", " + label + ", " + price;
    }
}
