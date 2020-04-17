package model.exceptions;

public class QuantityException extends Exception {
    private Integer wrongQuantity;

    public QuantityException(Integer wrongQuantity) {
        this.wrongQuantity = wrongQuantity;
    }
    public String getMessage() {
        return	"The value (" + wrongQuantity + ") proposed for quantity is invalid !";
    }
}
