package model;

import model.exceptions.TimeException;

import java.util.Date;

public class Service {
    private static int nbrServices = 1;
    private Integer serviceID;
    private Date startTime;
    private Date endTime;

    public Service(Date startTime, Date endTime) throws TimeException {
        serviceID = nbrServices;
        nbrServices++;
        this.startTime = startTime;
        setEndTime(endTime);
    }

    public void setEndTime(Date endTime) throws TimeException {
        if(endTime.before(startTime))
            throw new TimeException(endTime);
        else this.endTime = endTime;
    }
}
