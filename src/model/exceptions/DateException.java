package model.exceptions;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateException extends Exception {
    private GregorianCalendar wrongDate;
    private String precision;

    public DateException(GregorianCalendar wrongDate, String precision) {
        this.wrongDate = wrongDate;
        this.precision = precision;
    }

    public String getMessage(){
        String message = "";
        if(wrongDate != null){
            message = "La date " + wrongDate.get(Calendar.DAY_OF_MONTH)
                    + "/" + (wrongDate.get(Calendar.MONTH) + 1) + "/"
                    + wrongDate.get(Calendar.YEAR)
                    + " est invalide ! ";
        }

        if(precision != null){
            message += precision;
        }
        return message;
    }
}
