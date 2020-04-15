package model;

public class Drink {
    private String label;
    private Coffee coffee;
    private Integer size;
    private Boolean isCold;

    public Drink(String label, Coffee coffee, Integer size, Boolean isCold) {
        this.label = label;
        this.coffee = coffee;
        this.size = size;
        this.isCold = isCold;
    }


}
