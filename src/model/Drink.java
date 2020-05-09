package model;

import model.exceptions.StringInputException;

import java.util.ArrayList;

public class Drink {
    private String label;
    private Coffee coffee;
    private Boolean isCold;
    private ArrayList<Topping> supplements;

    public Drink(String label, Coffee coffee, Boolean isCold) {
        this.label = label;
        this.coffee = coffee;
        this.isCold = isCold;
        supplements = new ArrayList<>();
    }

    // pour la recherche des anciennes commandes
    public Drink(String label, Boolean isCold) {
        this.label = label;
        this.isCold = isCold;
        supplements = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public Boolean getCold() {
        return isCold;
    }

    public void addSupplement(Topping supplement) {
        supplements.add(supplement);
    }

    public void removeSupplement(Topping supplement) {
        supplements.remove(supplement);
    }

    public String toString(){
        return label + (isCold ? " froid " : " chaud ");
    }
}
