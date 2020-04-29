package model;

import java.util.ArrayList;

public class Drink {
    private String label;
    private Coffee coffee;
    private Boolean isCold;
    private ArrayList<Supplement> supplements;

    public Drink(String label, Coffee coffee, String size, Boolean isCold) {
        this.label = label;
        this.coffee = coffee;
        this.isCold = isCold;
        supplements = new ArrayList<>();
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

        res.append("(").append(isCold ? "froid" : "chaud").append(")\n");

        for (Supplement supplement : supplements) {
            res.append(supplement);
        }

        return res.toString();
    }
}
