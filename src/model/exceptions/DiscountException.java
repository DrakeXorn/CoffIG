package model.exceptions;

public class DiscountException extends Exception {
    private Double discountErrone;

    public DiscountException(Double discountErrone) {
        this.discountErrone = discountErrone;
    }

    public String getMessage(){
        return "La valeur " + discountErrone + " proposée pour la réduction est invalide ! " +
                "Elle doit être comprise entre 0 et 100%";
    }
}
