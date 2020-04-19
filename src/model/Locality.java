package model;

public class Locality {
    private Integer postalCode;
    private String city;

    public Locality(Integer postalCode, String city) {
        this.postalCode = postalCode;
        this.city = city;
    }

    public String toString(){
        return postalCode + " " + city;
    }
}
