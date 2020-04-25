package model;

import model.exceptions.IntegerInputException;

public class Locality {
    private Integer postalCode;
    private String city;

    public Locality(Integer postalCode, String city) {
        this.postalCode = postalCode;
        this.city = city;
    }

    public void setPostalCode(Integer postalCode) throws IntegerInputException {
        if(postalCode < 1000 || postalCode > 9999)
            throw new IntegerInputException(postalCode, "le code postal", "Le code postal doit être compris entre 1000 et 9999");
        this.postalCode = postalCode;
    }

    public String toString(){
        return postalCode + " " + city;
    }
}
