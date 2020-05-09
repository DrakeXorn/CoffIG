package model;

import java.util.ArrayList;

public class Drink {
    private String label;
    private Coffee coffee;
    private String size;
    private Boolean cold;
    private ArrayList<Supplement> supplements;

    public Drink(String label, Coffee coffee, String size, Boolean cold) {
        this.label = label;
        this.coffee = coffee;
        this.size = size;
        this.cold = cold;
        supplements = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public String getSize() {
        return size;
    }

    public Boolean isCold() {
        return cold;
    }

    public ArrayList<Supplement> getSupplements() {
        return supplements;
    }

    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    public void removeSupplement(Supplement supplement) {
        supplements.remove(supplement);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(label);

        res.append("(").append(cold ? "froid" : "chaud").append(")\n");

        for (Supplement supplement : supplements) {
            res.append(supplement);
        }

        return res.toString();
    }
}
