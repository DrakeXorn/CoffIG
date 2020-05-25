package model;

import model.exceptions.DoubleInputException;
import model.exceptions.IntegerInputException;
import model.exceptions.StringInputException;

import java.util.ArrayList;

public class DrinkOrdering {
    private Drink drink;
    private ArrayList<Topping> toppings;
    private Integer nbrPieces;
    private Double sellingPrice;
    private String size;

    // pour la recherche des anciennes commandes
    public DrinkOrdering(Drink drink, String size, Integer nbrPieces, Double sellingPrice) throws StringInputException, DoubleInputException, IntegerInputException {
        this.drink = drink;
        toppings = new ArrayList<>();
        setSize(size);
        setPieces(nbrPieces);
        setPrice(sellingPrice);
    }

    // pour la récation de commandes
    public DrinkOrdering(Drink drink, String size, Integer nbrPieces, Double sellingPrice, ArrayList<Topping> toppings) throws IntegerInputException, StringInputException, DoubleInputException {
        this(drink, size, nbrPieces, sellingPrice);
        this.toppings = toppings;
    }

    public Drink getDrink() {
        return drink;
    }

    public Integer getNbrPieces() {
        return nbrPieces;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public String getSize() {
        return size;
    }

    public void addTopping(Topping topping){
        toppings.add(topping);
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    private boolean allToppingsMatch(DrinkOrdering drinkOrdering) {
        if (drinkOrdering.getToppings().size() == toppings.size()) {
            for (int i = 0; i < toppings.size(); i++)
                if (!toppings.get(i).equals(drinkOrdering.getToppings().get(i)))
                    return false;
        } else return false;

        return true;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof DrinkOrdering &&
                ((object.toString().equals(toString())) ||
                (((DrinkOrdering) object).getSize().equals(size) && ((DrinkOrdering) object).getDrink().equals(drink) && allToppingsMatch((DrinkOrdering) object) && getSellingPrice().equals(((DrinkOrdering) object).getSellingPrice())));
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

    public void setSize(String size) throws StringInputException {
        if (!size.toLowerCase().equals("small") && !size.toLowerCase().equals("medium") && !size.toLowerCase().equals("large"))
            throw new StringInputException(size, "la taille", "La taille doit être small, medium ou large !");
        this.size = size;
    }

    public void addPieces(int number) {
        nbrPieces += number;
    }

    public double getPrice() {
        return nbrPieces * sellingPrice;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(nbrPieces + " * " + drink + "(" + size + ") : " + getPrice() + "€\n");

        for (Topping topping : toppings)
            res.append(topping);

        return res.toString();
    }
}
