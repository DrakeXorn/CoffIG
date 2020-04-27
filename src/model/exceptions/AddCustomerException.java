package model.exceptions;

public class AddCustomerException extends Exception {
    private String objet;
    private String exceptionMessage;

    public AddCustomerException(String objet, String message){
        exceptionMessage = message;
        this.objet = objet;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de l'ajout d'un " + objet + "\n" + exceptionMessage;
    }
}
