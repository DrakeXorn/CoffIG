package dataAccess;

import model.Employee;
import model.exceptions.*;

import java.util.ArrayList;

public interface EmployeeDataAccess {
    ArrayList<Employee> getAllEmployees() throws AllDataException, ConnectionException, DateException, CharacterInputException, StringInputException;
    Employee getManager() throws AllDataException, ConnectionException, StringInputException, DateException, CharacterInputException;
    void addEmployee(Employee employee) throws ConnectionException, AddDataException;
    ArrayList<Employee> getCurrentlyWorkingEmployees() throws AllDataException, ConnectionException, StringInputException, DateException, CharacterInputException;
    int getLastParkingSpaceNumber() throws ConnectionException, AddDataException;
}
