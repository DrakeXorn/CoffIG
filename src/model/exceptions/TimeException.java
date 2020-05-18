package model.exceptions;

import java.time.LocalTime;

public class TimeException extends Exception {
    private LocalTime wrongEndTime;

    public TimeException(LocalTime endTime) {
        wrongEndTime = endTime;
    }

    @Override
    public String getMessage() {
        return "La valeur " + wrongEndTime + " proposée pour la date de fin est invalide ! " +
                "Elle doit précéder la date de début.";
    }
}
