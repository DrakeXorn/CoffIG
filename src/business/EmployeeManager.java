package business;

import dataAccess.EmployeeDBAccess;
import dataAccess.EmployeeDataAccess;
import model.Assignment;
import model.Employee;
import model.exceptions.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class EmployeeManager {
    private EmployeeDataAccess employeeAccessor;

    public EmployeeManager() {
        employeeAccessor = new EmployeeDBAccess();
    }

    public ArrayList<Employee> getAllEmployees() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException {
        return employeeAccessor.getAllEmployees();
    }

    public Employee getManager() throws AllDataException, ConnectionException, StringInputException, DateException, CharacterInputException {
        return employeeAccessor.getManager();
    }

    public void addEmployee(Employee employee) throws AddDataException, ConnectionException, BusinessException {
        if (employee == null)
            throw new BusinessException("EmployeeManager", "l'ajout d'un employé", "L'employé");

        employeeAccessor.addEmployee(employee);
    }

    public ArrayList<Employee> getCurrentlyWorkingEmployees() throws ConnectionException, CharacterInputException, DateException, StringInputException, AllDataException {
        return employeeAccessor.getCurrentlyWorkingEmployees();
    }

    public int getLastParkingSpaceNumber() throws ConnectionException, AllDataException {
        return employeeAccessor.getLastParkingSpaceNumber();
    }

    public ArrayList<Assignment> searchAssignments(String identity, GregorianCalendar startDate, GregorianCalendar endDate) throws AllDataException, ConnectionException, TimeException, BusinessException {
        if (identity == null)
            throw new BusinessException("EmployeeManager", "la recherche de services", "L'identité de l'employé");
        if (startDate == null)
            throw new BusinessException("EmployeeManager", "la recherche de services", "La date de début");
        if (endDate == null)
            throw new BusinessException("EmployeeManager", "la recherche de services", "La date de fin");

        return employeeAccessor.searchAssignments(identity, startDate, endDate);
    }
}
