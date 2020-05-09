package business;

import dataAccess.AssignmentDBAccess;
import dataAccess.AssignmentDataAccess;
import model.Assignment;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;
import model.exceptions.TimeException;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AssignmentManager {
    private AssignmentDataAccess dataAccessor;

    public AssignmentManager() {
        dataAccessor = new AssignmentDBAccess();
    }

    public ArrayList<Assignment> searchAssignments(String identity, GregorianCalendar startDate, GregorianCalendar endDate) throws AllDataException, ConnectionException, TimeException {
        return dataAccessor.searchAssignments(identity, startDate, endDate);
    }
}
