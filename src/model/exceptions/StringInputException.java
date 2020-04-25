package model.exceptions;

public class StringInputException extends Exception {
    private String wrongValue;
    private String nameValue;
    private String precision;

    public StringInputException(String wrongValue, String nameValue, String precision) {
        this.wrongValue = wrongValue;
        this.nameValue = nameValue;
        this.precision = precision;
    }

    public StringInputException(String wrongValue, String nameValue) {
        this(wrongValue, nameValue, "");
    }

    public String getMessage() {
        return nameValue != null ? "La valeur ("+ wrongValue +") proposée pour "+ nameValue +" est invalide !\n" + precision : precision;
    }
}
