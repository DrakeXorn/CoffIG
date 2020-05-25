package model.exceptions;

public class BooleanInputException extends Exception{

    private String precision;

    public BooleanInputException(String precision) {

        this.precision = precision;
    }

    public String getMessage() {
        return precision;
    }


}
