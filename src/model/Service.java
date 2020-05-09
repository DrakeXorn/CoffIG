package model;

import model.exceptions.TimeException;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Service {
    private static int nbrServices = 1;
    private Integer serviceID;
    private LocalTime startTime;
    private LocalTime endTime;

    public Service(LocalTime startTime, LocalTime endTime) throws TimeException {
        serviceID = nbrServices;
        nbrServices++;
        this.startTime = startTime;
        setEndTime(endTime);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) throws TimeException {
        if(endTime.isBefore(startTime))
            throw new TimeException(endTime);
        else this.endTime = endTime;
    }
}
