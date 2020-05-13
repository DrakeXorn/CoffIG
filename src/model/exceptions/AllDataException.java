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

        return "Erreur lors de " + attemptDescription + " dans la base de données :\n" +
                exceptionMessage;
    }
}
