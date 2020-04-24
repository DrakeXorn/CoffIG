package model;

public class Locality {
    private Integer postalCode;
    private String city;

    public Locality(Integer postalCode, String city) {
        this.postalCode = postalCode;
        this.city = city;
    }

    public void setPostalCode(Integer postalCode) {
        if(postalCode < 1000 || postalCode > 9999)
            // throw exception
        this.postalCode = postalCode;
    }

    public String toString(){
        return postalCode + " " + city;
    }
}
