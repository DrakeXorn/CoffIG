package model.exceptions;

public class ModifyException extends Exception {
    private String objet;
    private String exceptionMessage;

    public ModifyException(String objet, String message){
        exceptionMessage = message;
        this.objet = objet;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la modification d'un " + objet + "\n" + exceptionMessage;
    }
}
