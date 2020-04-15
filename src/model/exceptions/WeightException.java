package model.exceptions;

public class WeightException extends Exception {
    private Double weightErrone;

    public WeightException(Double weightErrone) {
        this.weightErrone = weightErrone;
    }

    public String getMessage(){
        return "La valeur " + weightErrone + " proposée pour le poids est invalide !";
    }
}
