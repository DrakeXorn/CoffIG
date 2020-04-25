package model;

import model.exceptions.StringInputException;

import java.util.ArrayList;

public class Drink {
    private String label;
    private Coffee coffee;
    private String size;
    private Boolean isCold;
    private ArrayList<Supplement> supplements;

    public Drink(String label, Coffee coffee, String size, Boolean isCold) throws StringInputException {
        this.label = label;
        this.coffee = coffee;
        setSize(size);
        this.isCold = isCold;
        supplements = new ArrayList<>();
    }

    public void setSize(String size) throws StringInputException {
        if (!size.toLowerCase().equals("small") && !size.toLowerCase().equals("medium") && !size.toLowerCase().equals("large"))
            throw new StringInputException(size, "la taille", "La taille doit être small, medium ou large !");
        this.size = size;
    }

    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    public void removeSupplement(Supplement supplement) {
        supplements.remove(supplement);
    }
}
