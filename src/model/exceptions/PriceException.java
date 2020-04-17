package model.exceptions;

public class PriceException extends Exception{
    private Double wrongPrice;

    public PriceException(Double wrongPrice) {
        this.wrongPrice = wrongPrice;
    }

    public String getMessage() {
        return "La valeur " + wrongPrice + " proposée pour le prix est invalide !";
    }
}
