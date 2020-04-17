package model.exceptions;

import java.util.Date;

public class TimeException extends Exception {
    private Date wrongEndTime;

    public TimeException(Date endTime) {
        wrongEndTime = endTime;
    }

    @Override
    public String getMessage() {
        return "La valeur " + wrongEndTime + " proposée pour la date de fin est invalide ! " +
                "Elle doit précéder la date de début.";
    }
}
