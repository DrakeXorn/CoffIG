package model.exceptions;

public class AddDataException extends Exception {
    private String message;
    private String dataAdded;

    public AddDataException(String message, String dataAdded) {
        this.message = message;
        this.dataAdded = dataAdded;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de l'inscription du/de la/d' " + dataAdded + " dans la base de données :\n" + message;
    }
}
