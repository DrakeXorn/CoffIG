package model.exceptions;

public class IntegerInputException extends Exception {
    private Integer wrongValue;
    private String nameValue;
    private String precision;

    public IntegerInputException(Integer wrongValue, String nameValue, String precision) {
        this.wrongValue = wrongValue;
        this.nameValue = nameValue;
        this.precision = precision;
    }

    public IntegerInputException(Integer wrongValue, String nameValue) {
        this(wrongValue, nameValue, "");
    }

    public String getMessage() {
        return	"La valeur ("+ wrongValue +") proposée pour "+ nameValue +" est invalide !\n" + precision;
    }
}
