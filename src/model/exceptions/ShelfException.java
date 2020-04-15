package model.exceptions;

public class ShelfException extends Exception {
    private Integer shelfErrone;

    public ShelfException(Integer shelfErrone) {
        this.shelfErrone = shelfErrone;
    }
    public String getMessage() {
        return	"The value ("+shelfErrone+") proposed for shelf is invalid !";
    }
}
