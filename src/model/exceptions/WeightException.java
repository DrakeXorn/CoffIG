package model.exceptions;

public class WeightException extends Exception {
    private Double wrongWeight;

    public WeightException(Double wrongWeight) {
        this.wrongWeight = wrongWeight;
    }

    public String getMessage(){
        return "La valeur " + wrongWeight + " proposée pour le poids est invalide !";
    }
}
