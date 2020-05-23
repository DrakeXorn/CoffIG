package controller;

import business.EmployeeManager;
import model.Assignment;
import model.Employee;
import model.exceptions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class EmployeeController {
    private EmployeeManager manager;

    public EmployeeController() {
        manager = new EmployeeManager();
    }

    public ArrayList<Employee> getAllEmployees() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException {
        return manager.getAllEmployees();
    }

    public Employee getManager() throws AllDataException, ConnectionException, StringInputException, DateException, CharacterInputException {
        return manager.getManager();
    }

    public void addEmployee(Employee employee) throws AddDataException, ConnectionException {
        manager.addEmployee(employee);
    }
  
    public ArrayList<Employee> getCurrentlyWorkingEmployees() throws ConnectionException, AllDataException, CharacterInputException, DateException, StringInputException {
        return manager.getCurrentlyWorkingEmployees();
    }

    public int getLastParkingSpaceNumber() throws ConnectionException, AddDataException {
        return manager.getLastParkingSpaceNumber();
    }

    public ArrayList<Assignment> searchAssignments(String identity, GregorianCalendar startDate, GregorianCalendar endDate) throws AllDataException, ConnectionException, TimeException {
        return manager.searchAssignments(identity, startDate, endDate);
    }
}
