package model.exceptions;

public class AddException extends Exception {
    private String object;
    private String exceptionMessage;

    public AddException(String object, String message){
        exceptionMessage = message;
        this.object = object;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de l'ajout d'un " + object + "\n" + exceptionMessage;
    }
}
