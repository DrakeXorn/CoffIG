package controller;

import business.EmployeeManager;
import model.Employee;
import model.exceptions.*;

import java.util.ArrayList;

public class EmployeeController {
    private EmployeeManager manager;

    public EmployeeController() {
        manager = new EmployeeManager();
    }

    public ArrayList<Employee> getAllEmployees() throws AllDataException, ConnectionException, AddDataException, CharacterInputException, DateException, StringInputException {
        return manager.getAllEmployees();
    }
}
