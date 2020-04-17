package model.exceptions;

public class SizeException extends Exception {
    private String wrongSize;

    public SizeException(String wrongSize) {
        this.wrongSize = wrongSize;
    }

    @Override
    public String getMessage() {
        return "Mauvaise taille. Vous avez choisi " + wrongSize + " au lieu de small, medium ou large.";
    }
}
