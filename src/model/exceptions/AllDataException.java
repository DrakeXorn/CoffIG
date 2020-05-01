package model.exceptions;

public class AllDataException extends Exception {
    private String exceptionMessage;
    private String attemptDescription;

    public AllDataException(String exceptionMessage, String attemptDescription) {
        this.exceptionMessage = exceptionMessage;
        this.attemptDescription = attemptDescription;
    }

    @Override
    public String getMessage() {
        StringBuilder res = new StringBuilder("Erreur lors de ");

        res.append(attemptDescription).append(" des cafés de la base de données :\n");
        res.append(exceptionMessage);

        return res.toString();
    }
}
