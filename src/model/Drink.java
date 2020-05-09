package model;

import java.util.ArrayList;

public class Drink {
    private String label;
    private Coffee coffee;
    private Boolean cold;
    private ArrayList<Topping> supplements;

    public Drink(String label, Coffee coffee, Boolean cold) {
        this.label = label;
        this.coffee = coffee;
        this.cold = cold;
        supplements = new ArrayList<>();
    }

    // pour la recherche des anciennes commandes
    public Drink(String label, Boolean cold) {
        this.label = label;
        this.cold = cold;
        supplements = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public Boolean isCold() {
        return cold;
    }

    public ArrayList<Topping> getSupplements() {
        return supplements;
    }

    public void addSupplement(Topping supplement) {
        supplements.add(supplement);
    }

    public void removeSupplement(Topping supplement) {
        supplements.remove(supplement);
    }

    public String toString(){
        return label + (cold ? " froid " : " chaud ");
    }
}
