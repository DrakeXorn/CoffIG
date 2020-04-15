package model;

import java.util.GregorianCalendar;

public class Assignment {
    private Employee employee;
    private Service service;
    private GregorianCalendar serviceDate;

    public Assignment(Employee employee, Service service, GregorianCalendar serviceDate) {
        this.employee = employee;
        this.service = service;
        this.serviceDate = serviceDate;
    }
}
