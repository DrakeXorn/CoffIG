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

    public ArrayList<Employee> getAllEmployees() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException {
        return manager.getAllEmployees();
    }

    public void addEmployee(Employee employee) throws AddDataException, ConnectionException {
        manager.addEmployee(employee);
    }
  
    public ArrayList<Employee> getCurrentlyWorkingEmployees() throws ConnectionException, AllDataException, CharacterInputException, DateException, StringInputException {
        return manager.getCurrentlyWorkingEmployees();
    }
}
