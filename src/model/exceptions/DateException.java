package model.exceptions;

import java.util.GregorianCalendar;

public class DateException extends Exception {
    private GregorianCalendar wrongDate;

    public DateException(GregorianCalendar wrongDate) {
        this.wrongDate = wrongDate;
    }

    public String getMessage(){
        return "La valeur " + wrongDate + " proposée pour la date de fin est invalide ! " +
                "Elle doit précéder la date de début.";
    }
}
