package model;

import model.exceptions.DoubleInputException;
import model.exceptions.IntegerInputException;

public class FoodOrdering {
    private Food food;
    private Order order;
    private Integer nbrPieces;
    private Double sellingPrice;

    public FoodOrdering(Food food, Order order, Integer nbrPieces, Double sellingPrice) {
        this.food = food;
        this.order = order;
        order.addFoodOrdering(this);
        this.nbrPieces = nbrPieces;
        this.sellingPrice = sellingPrice;
    }

    // pour le recherche des anciennes commandes
    public FoodOrdering(Food food, Integer nbrPieces, Double sellingPrice) throws DoubleInputException, IntegerInputException {
        this.food = food;
        setPieces(nbrPieces);
        setPrice(sellingPrice);
    }

    public Integer getNbrPieces() {
        return nbrPieces;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof FoodOrdering && ((FoodOrdering)object).toString().equals(toString());
    }

    public void setPieces(Integer nbrPieces) throws IntegerInputException {
        if (nbrPieces < 0)
            throw new IntegerInputException(nbrPieces, "le nombre d'article(s)", "Le nombre d'aliment doit être positif et différent de 0 !");
        this.nbrPieces = nbrPieces;
    }

    public void setPrice(Double price) throws DoubleInputException {
        if (price < 0)
            throw new DoubleInputException(price, "le prix", "Le prix doit être positif et différent de 0 !");
        sellingPrice = price;
    }

    public String toString() {
        return food + "\tnombre d'article(s) : " + nbrPieces + "\tprix unitaire : " + sellingPrice + ")";
    }
}
