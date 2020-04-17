package model.exceptions;

public class IntensityException extends Exception {
    private Integer wrongIntensity;

    public IntensityException(Integer wrongIntensity) {
        this.wrongIntensity = wrongIntensity;
    }

    public String getMessage(){
        return "La valeur " + wrongIntensity + " proposée pour l'intensité est invalide !";
    }
}
