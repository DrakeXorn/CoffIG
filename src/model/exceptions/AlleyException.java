package model.exceptions;

public class AlleyException extends Exception {
    private Integer alleyErrone;

    public AlleyException(Integer alleyErrone) {
        this.alleyErrone = alleyErrone;
    }
    public String getMessage() {
        return	"The value ("+alleyErrone+") proposed for alley is invalid !";
    }
}
