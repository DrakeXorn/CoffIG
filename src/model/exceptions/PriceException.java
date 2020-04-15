package model.exceptions;

public class PriceException extends Exception{
    private Double priceErrone;

    public PriceException(Double priceErrone) {
        this.priceErrone = priceErrone;
    }

    public String getMessage(){
        return "La valeur " + priceErrone + " proposée pour le prix est invalide !";
    }
}
