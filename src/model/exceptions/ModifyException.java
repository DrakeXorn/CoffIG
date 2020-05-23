package model.exceptions;

public class ModifyException extends Exception {
    private String object;
    private String exceptionMessage;
    private String modifyAttempt;

    public ModifyException(String object, String message, String modifyAttempt){
        exceptionMessage = message;
        this.object = object;
        this.modifyAttempt = modifyAttempt;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la " + modifyAttempt + " de/d'un " + object + "\n" + exceptionMessage;
    }
}
