package model;

import model.exceptions.DoubleInputException;

public class Food {
    private Integer foodId;
    private String label;
    private Double price;
    private StockLocation stockLocation;

    // pour la récupération des foods dans le BD
    public Food(Integer foodId, String label, Double price, StockLocation stockLocation) throws DoubleInputException {
        this.foodId = foodId;
        this.label = label;
        setPrice(price);
        this.stockLocation = stockLocation;
    }

    // pour la recherche des anciennes commandes
    public Food(Integer foodID, String label) {
        this.foodId = foodID;
        this.label = label;
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

    @Override
    public boolean equals(Object object) {
        return object instanceof Food && foodId.equals(((Food) object).getFoodId());
    }

    public String toString() {
        return getLabel();
    }
}
