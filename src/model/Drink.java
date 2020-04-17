package model;

import model.exceptions.SizeException;

import java.util.ArrayList;

public class Drink {
    private String label;
    private Coffee coffee;
    private String size;
    private Boolean isCold;
    private ArrayList<Supplement> supplements;

    public Drink(String label, Coffee coffee, String size, Boolean isCold) throws SizeException {
        this.label = label;
        this.coffee = coffee;
        setSize(size);
        this.isCold = isCold;
        supplements = new ArrayList<>();
    }

    public void setSize(String size) throws SizeException {
        if (!size.toLowerCase().equals("small") && !size.toLowerCase().equals("medium") && !size.toLowerCase().equals("large"))
            throw new SizeException(size);
        this.size = size;
    }

    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    public void removeSupplement(Supplement supplement) {
        supplements.remove(supplement);
    }
}
