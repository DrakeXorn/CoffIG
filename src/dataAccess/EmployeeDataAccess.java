package dataAccess;

import model.Employee;
import model.exceptions.*;

import java.util.ArrayList;

public interface EmployeeDataAccess {
    ArrayList<Employee> getAllEmployees() throws AllDataException, ConnectionException, DateException, CharacterInputException, StringInputException;
    void addEmployee(Employee employee) throws ConnectionException, AddDataException;
    void removeEmployee(Employee employee);
    void updateEmployee(Employee employee);
    ArrayList<Employee> getCurrentlyWorkingEmployees() throws AllDataException, ConnectionException, StringInputException, DateException, CharacterInputException;
}
