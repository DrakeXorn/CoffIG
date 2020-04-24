package model.exceptions;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateException extends Exception {
    private GregorianCalendar wrongDate;
    private GregorianCalendar previousDate;

    public DateException(GregorianCalendar wrongDate, GregorianCalendar previousDate) {
        this.wrongDate = wrongDate;
        this.previousDate = previousDate;
    }

    public String getMessage(){
        return "La date " + wrongDate.get(Calendar.DAY_OF_MONTH)
                + "/" + (wrongDate.get(Calendar.MONTH) + 1) + "/"
                + wrongDate.get(Calendar.YEAR)
                + " est invalide ! Elle ne doit pas précèder le " + previousDate.get(Calendar.DAY_OF_MONTH)
                + "/" + (previousDate.get(Calendar.MONTH) + 1) + "/"
                + previousDate.get(Calendar.YEAR);
    }
}
