package model;

import model.exceptions.DoubleInputException;

public class Food {
    private static int nbrFoods = 1;
    private Integer foodId;
    private String label;
    private Double price;
    private StockLocation stockLocation;

    public Food(Integer foodId, String label, Double price, StockLocation stockLocation) throws DoubleInputException {
        this.foodId = foodId;
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

    // pour la recherche des anciennes commandes
    public Food(Integer foodID, String label) {
        this.foodId = foodID;
        if (foodID > nbrFoods)
            nbrFoods = foodID + 1;
        this.label = label;
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

    @Override
    public boolean equals(Object object) {
        return object instanceof Food && foodId.equals(((Food) object).getFoodId());
    }

    public String toString() {
        return getLabel();
    }
}
