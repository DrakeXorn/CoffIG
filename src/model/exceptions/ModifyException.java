package model.exceptions;

public class ModifyException extends Exception {
    private String object;
    private String exceptionMessage;

    public ModifyException( String message, String object){
        exceptionMessage = message;
        this.object = object;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la modification d'un " + object + "\n" + exceptionMessage;
    }
}
