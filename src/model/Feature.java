package model;

public class Feature {
    private String label;

    public Feature(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
