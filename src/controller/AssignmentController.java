package controller;

import business.AssignmentManager;
import model.Assignment;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;
import model.exceptions.TimeException;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AssignmentController {
    private AssignmentManager manager;

    public AssignmentController() {
        manager = new AssignmentManager();
    }

    public ArrayList<Assignment> searchAssignments(String identity, GregorianCalendar startDate, GregorianCalendar endDate) throws AllDataException, ConnectionException, TimeException {
        return manager.searchAssignments(identity, startDate, endDate);
    }
}
