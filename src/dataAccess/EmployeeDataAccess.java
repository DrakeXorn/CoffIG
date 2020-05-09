package dataAccess;

import model.Employee;
import model.Order;
import model.exceptions.*;

import java.util.ArrayList;

public interface EmployeeDataAccess {
    ArrayList<Employee> getAllEmployees() throws AllDataException, ConnectionException, AddDataException, DateException, CharacterInputException, StringInputException;
    boolean addEmployee(Employee employee) throws ConnectionException, AddDataException;
    boolean removeEmployee(Employee employee) throws ConnectionException, AddDataException;
    boolean updateEmployee(Employee employee) throws ConnectionException, AddDataException;
}
