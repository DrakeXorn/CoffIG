package model.exceptions;

public class DoubleInputException extends Exception   {
    private Double wrongValue;
    private String nameValue;
    private String precision;

    public DoubleInputException(Double wrongValue, String nameValue, String precision) {
        this.wrongValue = wrongValue;
        this.nameValue = nameValue;
        this.precision = precision;
    }

    public DoubleInputException(Double wrongValue, String nameValue) {
        this(wrongValue, nameValue, "");
    }

    public String getMessage() {
        return	"La valeur ("+ wrongValue +") proposée pour "+ nameValue +" est invalide !\n" + precision;
    }
}
