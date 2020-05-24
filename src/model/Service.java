package model;

import model.exceptions.TimeException;
import java.time.LocalTime;

public class Service {
    private LocalTime startTime;
    private LocalTime endTime;

    public Service(LocalTime startTime, LocalTime endTime) throws TimeException {
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
