package business;

import dataAccess.EmployeeDBAccess;
import dataAccess.EmployeeDataAccess;
import model.Employee;
import model.exceptions.*;

import java.util.ArrayList;

public class EmployeeManager {
    private EmployeeDataAccess employeeAccessor;

    public EmployeeManager() {
        employeeAccessor = new EmployeeDBAccess();
    }

    public ArrayList<Employee> getAllEmployees() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException {
        return employeeAccessor.getAllEmployees();
    }
}
