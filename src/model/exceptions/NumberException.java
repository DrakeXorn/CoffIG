package model.exceptions;

public class NumberException extends Exception {
    private Integer numberErrone;

    public NumberException(Integer numberErrone) {
        this.numberErrone = numberErrone;
    }
    public String getMessage() {
        return	"The value ("+numberErrone+") proposed for number is invalid !";
    }
}
