package model.exceptions;

public class ShelfException extends Exception {
    private Integer wrongShelf;

    public ShelfException(Integer wrongShelf) {
        this.wrongShelf = wrongShelf;
    }
    public String getMessage() {
        return	"The value ("+ wrongShelf +") proposed for shelf is invalid !";
    }
}
