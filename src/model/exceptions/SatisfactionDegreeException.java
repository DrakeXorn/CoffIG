package model.exceptions;

public class SatisfactionDegreeException extends Exception {
    private Integer satisfactionDegreeErrone;

    public SatisfactionDegreeException(Integer satisfactionDegreeErrone) {
        this.satisfactionDegreeErrone = satisfactionDegreeErrone;
    }
    public String getMessage() {
        return	"The value ("+satisfactionDegreeErrone+") proposed for satisfaction level is invalid !";
    }
}