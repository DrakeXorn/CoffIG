package model;

import model.exceptions.DoubleInputException;
import model.exceptions.IntegerInputException;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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

    // pour la recherche des anciennes commandes
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
        return object instanceof FoodOrdering && food.toString().equals(((FoodOrdering) object).getFood().toString());
    }

    public void setPieces(Integer nbrPieces) throws IntegerInputException {
        if (nbrPieces < 0)
            throw new IntegerInputException(nbrPieces, "le nombre d'article(s)", "Le nombre d'aliments doit être positif et différent de 0 !");
        this.nbrPieces = nbrPieces;
    }

    public void setPrice(Double price) throws DoubleInputException {
        if (price < 0)
            throw new DoubleInputException(price, "le prix", "Le prix doit être positif et différent de 0 !");
        sellingPrice = price;
    }

    public void addPieces(int number) {
        nbrPieces += number;
    }

    public double price() {
        return nbrPieces * sellingPrice;
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("0.00");

        formatter.setRoundingMode(RoundingMode.CEILING);
        return nbrPieces + " * " + food + " :  " + formatter.format(price()) + "€";
    }
}
