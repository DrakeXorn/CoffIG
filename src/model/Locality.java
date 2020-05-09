package model;

import model.exceptions.IntegerInputException;

public class Locality {
    private Integer postalCode;
    private String city;

    public Locality(Integer postalCode, String city) {
        this.postalCode = postalCode;
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Locality && object.toString().equals(toString());
    }

    public String toString(){
        return postalCode + " " + city;
    }
}
