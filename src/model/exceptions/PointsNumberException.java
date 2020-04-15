package model.exceptions;

public class PointsNumberException extends Exception {
    private Integer pointsNumberErrone;

    public PointsNumberException(Integer pointsNumberErrone) {
        this.pointsNumberErrone = pointsNumberErrone;
    }
    public String getMessage() {
        return	"The value ("+pointsNumberErrone+") proposed for points Number is invalid !";
    }
}
