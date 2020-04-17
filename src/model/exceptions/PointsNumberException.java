package model.exceptions;

public class PointsNumberException extends Exception {
    private Integer wrongPointsNumber;

    public PointsNumberException(Integer wrongPointsNumber) {
        this.wrongPointsNumber = wrongPointsNumber;
    }
    public String getMessage() {
        return	"The value ("+ wrongPointsNumber +") proposed for points Number is invalid !";
    }
}
