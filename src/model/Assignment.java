package model;

import java.util.GregorianCalendar;

public class Assignment {
    private Service service;
    private GregorianCalendar serviceDate;

    public Assignment(Service service, GregorianCalendar serviceDate) {
        this.service = service;
        this.serviceDate = serviceDate;
    }

    public Service getService() {
        return service;
    }

    public GregorianCalendar getServiceDate() {
        return serviceDate;
    }
}
