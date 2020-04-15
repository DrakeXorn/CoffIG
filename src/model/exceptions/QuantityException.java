package model.exceptions;

public class QuantityException extends Exception {
    private Integer quantityErrone;

    public QuantityException(Integer quantityErrone) {
        this.quantityErrone = quantityErrone;
    }
    public String getMessage() {
        return	"The value (" + quantityErrone + ") proposed for quantity is invalid !";
    }
}
