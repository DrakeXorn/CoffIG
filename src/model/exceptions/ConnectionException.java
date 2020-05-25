package model.exceptions;

public class ConnectionException extends Exception {
    private String exceptionMessage;

    public ConnectionException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String getMessage() {

        return "Erreur lors de la connexion à la base de données :\n" + exceptionMessage;
    }
}
