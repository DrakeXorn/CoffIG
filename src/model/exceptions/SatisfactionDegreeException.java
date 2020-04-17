package model.exceptions;

public class SatisfactionDegreeException extends Exception {
    private Integer wrongSatisfactionDegree;

    public SatisfactionDegreeException(Integer wrongSatisfactionDegree) {
        this.wrongSatisfactionDegree = wrongSatisfactionDegree;
    }
    public String getMessage() {
        return	"The value ("+ wrongSatisfactionDegree +") proposed for satisfaction level is invalid !";
    }
}