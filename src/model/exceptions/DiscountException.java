package model.exceptions;

public class DiscountException extends Exception {
    private Double wrongDiscount;

    public DiscountException(Double wrongDiscount) {
        this.wrongDiscount = wrongDiscount;
    }

    public String getMessage(){
        return "La valeur " + wrongDiscount + " proposée pour la réduction est invalide ! " +
                "Elle doit être comprise entre 0 et 100%";
    }
}
