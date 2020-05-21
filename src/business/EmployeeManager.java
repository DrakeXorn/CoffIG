package business;

import dataAccess.EmployeeDBAccess;
import dataAccess.EmployeeDataAccess;
import model.Employee;
import model.exceptions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeManager {
    private EmployeeDataAccess employeeAccessor;

    public EmployeeManager() {
        employeeAccessor = new EmployeeDBAccess();
    }

    public ArrayList<Employee> getAllEmployees() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException {
        return employeeAccessor.getAllEmployees();
    }

    public Employee getManager() throws AllDataException, ConnectionException, IOException, SQLException, StringInputException, DateException, CharacterInputException {
        return employeeAccessor.getManager();
    }

    public void addEmployee(Employee employee) throws AddDataException, ConnectionException {
        employeeAccessor.addEmployee(employee);
    }

    public ArrayList<Employee> getCurrentlyWorkingEmployees() throws ConnectionException, CharacterInputException, DateException, StringInputException, AllDataException {
        return employeeAccessor.getCurrentlyWorkingEmployees();
    }

    public int getLastParkingSpaceNumber() throws ConnectionException, AddDataException {
        return employeeAccessor.getLastParkingSpaceNumber();
    }
}
