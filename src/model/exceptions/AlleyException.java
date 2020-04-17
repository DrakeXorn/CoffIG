package model.exceptions;

public class AlleyException extends Exception {
    private Integer wrongAlley;

    public AlleyException(Integer wrongAlley) {
        this.wrongAlley = wrongAlley;
    }
    public String getMessage() {
        return	"The value ("+ wrongAlley +") proposed for alley is invalid !";
    }
}
