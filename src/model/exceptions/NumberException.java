package model.exceptions;

public class NumberException extends Exception {
    private Integer wrongNumber;

    public NumberException(Integer wrongNumber) {
        this.wrongNumber = wrongNumber;
    }
    public String getMessage() {
        return	"The value ("+ wrongNumber +") proposed for number is invalid !";
    }
}
