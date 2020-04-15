package model.exceptions;

import java.util.GregorianCalendar;

public class DateException extends Exception {
    private GregorianCalendar date;

    public DateException(GregorianCalendar date) {
        this.date = date;
    }

    public String getMessage(){
        return "La valeur " + date + " proposée pour la date de fin est invalide ! " +
                "Elle doit précéder la date de début.";
    }
}
