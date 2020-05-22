package model.exceptions;

public class ClosedConnexion extends Exception{
    private String exceptionMessage;

    public ClosedConnexion(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String getMessage() {

        return "Erreur lors de la fermeture de la connexion à la base de données :\n" + exceptionMessage;
    }
}
