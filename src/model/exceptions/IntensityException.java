package model.exceptions;

public class IntensityException extends Exception {
    private Integer intensityErrone;

    public IntensityException(Integer intensityErrone) {
        this.intensityErrone = intensityErrone;
    }

    public String getMessage(){
        return "La valeur " + intensityErrone + " proposée pour l'intensité est invalide !";
    }
}
