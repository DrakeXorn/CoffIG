package dataAccess;

import model.Assignment;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;
import model.exceptions.TimeException;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface AssignmentDataAccess {
    ArrayList<Assignment> searchAssignments(String identity, GregorianCalendar startDate, GregorianCalendar endDate) throws AllDataException, ConnectionException, TimeException;
}
