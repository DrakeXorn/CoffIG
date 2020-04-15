package model.exceptions;

public class FoodIDException extends Exception {
    private Integer foosIDErrone;

    public FoodIDException(Integer foosIDErrone) {
        this.foosIDErrone = foosIDErrone;
    }
    public String getMessage() {
        return	"The value ("+foosIDErrone+") proposed for foos ID is invalid !";
    }
}
