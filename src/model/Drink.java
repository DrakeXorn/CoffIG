package model;

public class Drink {
    private String label;
    private Coffee coffee;
    private Boolean cold;

    // pour la récupération de tous les drinks
    public Drink(String label, Coffee coffee, Boolean cold) {
        this.label = label;
        this.coffee = coffee;
        this.cold = cold;
    }

    // pour la recherche des anciennes commandes
    public Drink(String label, Boolean cold) {
        this(label, null, cold);
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

    public String toString(){
        return label + " (" + (cold ? "froid" : "chaud") + ")";
    }
}
