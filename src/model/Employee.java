package model;

import model.exceptions.*;

import java.util.*;

public class Employee extends User {
    private GregorianCalendar hireDate;
    private GregorianCalendar endContractDate;
    private Boolean isEmployeeOfMonth;
    private Double discount;
    private static int nbrParkingSpaces = 1;
    private Integer parkingSpaceNumber;
    private Employee manager;
    private ArrayList<Assignment> assignments;

    public Employee(String password, String lastName, String firstName, String secondName, String maidenName,
                    GregorianCalendar birthDate, String streetName, Locality locality, String email, String phoneNbr,
                    Character gender, GregorianCalendar hireDate, GregorianCalendar endContractDate,
                    Boolean isEmployeeOfMonth, Double discount, Employee manager, Integer parkingSpaceNumber)
            throws DateException, DoubleInputException, StringInputException, CharacterInputException, AllDataException, ConnectionException {
        super(password, lastName, firstName, secondName, maidenName, birthDate, streetName, locality, email, phoneNbr, gender);
        this.hireDate = hireDate;
        setEndContractDate(endContractDate);
        this.isEmployeeOfMonth = isEmployeeOfMonth;
        setDiscount(discount);
        this.manager = manager;
        assignments = new ArrayList<>();

        this.parkingSpaceNumber = parkingSpaceNumber;
        if (parkingSpaceNumber > nbrParkingSpaces)
            nbrParkingSpaces = parkingSpaceNumber + 1;
    }

    public Employee(String password, String lastName, String firstName, String secondName, String maidenName,
                    GregorianCalendar birthDate, String streetName, Locality locality, String email, String phoneNbr,
                    Character gender, GregorianCalendar hireDate, GregorianCalendar endContractDate,
                    Boolean isEmployeeOfMonth, Double discount, Employee manager, boolean wantsParkingSpace)
            throws DateException, DoubleInputException, StringInputException, CharacterInputException, AllDataException, ConnectionException {
        super(password, lastName, firstName, secondName, maidenName, birthDate, streetName, locality, email, phoneNbr,
                gender);
        this.hireDate = hireDate;
        setEndContractDate(endContractDate);
        this.isEmployeeOfMonth = isEmployeeOfMonth;
        setDiscount(discount);
        this.manager = manager;
        assignments = new ArrayList<>();

        if (wantsParkingSpace) {
            parkingSpaceNumber = nbrParkingSpaces;
            nbrParkingSpaces++;
        }
    }

    public Employee(String password, String lastName, String firstName, String secondName, String maidenName,
                    GregorianCalendar birthDate, String streetName, Locality locality, String email, String phoneNbr,
                    Character gender, GregorianCalendar hireDate, GregorianCalendar endContractDate,
                    Boolean isEmployeeOfMonth, Double discount, Employee manager)
            throws DateException, DoubleInputException, StringInputException, CharacterInputException, AllDataException, ConnectionException {
        this(password, lastName, firstName, secondName, maidenName, birthDate, streetName, locality, email, phoneNbr,
                gender, hireDate, endContractDate, isEmployeeOfMonth, discount, manager, Boolean.FALSE);
    }

    public Employee(Integer employeeId, String password, String lastName, String firstName, GregorianCalendar birthDate, String streetName, Locality locality, String email, String phone, Character gender, GregorianCalendar hireDate, Boolean isEmployeeOfMonth, Double discount, Integer parkingSpaceNumber) throws CharacterInputException, DateException, StringInputException, AllDataException, ConnectionException {
        super(employeeId, password, lastName, firstName, null, null, birthDate, streetName, locality, email, phone, gender);
        this.isEmployeeOfMonth = isEmployeeOfMonth;
        this.discount = discount;
        this.hireDate = hireDate;
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public GregorianCalendar getHireDate() {
        return hireDate;
    }

    public GregorianCalendar getEndContractDate() {
        return endContractDate;
    }

    public Boolean getEmployeeOfMonth() {
        return isEmployeeOfMonth;
    }

    public Double getDiscount() {
        return discount;
    }

    public int getNbrParkingSpaces() {
        return nbrParkingSpaces;
    }

    public Integer getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public Employee getManager() {
        return manager;
    }

    public void setDiscount(Double discount) throws DoubleInputException {
        if (discount < 0 || discount > 100)
            throw new DoubleInputException(discount, "la remise", "Elle doit être comprise entre 0 et 100%");
        this.discount = discount;
    }

    public void setEndContractDate(GregorianCalendar endContractDate) throws DateException {
        if(endContractDate != null){
            if(endContractDate.before(hireDate))
                throw new DateException(endContractDate, "La date de fin de contrat ne doit pas se trouver avant la date de début !");
            this.endContractDate = endContractDate;
        }
    }

    public void setParkingSpaceNumber(Integer parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void addAssignments(ArrayList<Assignment> newAssignments) {
        assignments.addAll(newAssignments);
    }

    public void removeAssignments(ArrayList<Assignment> assignmentsToDelete) {
        assignments.removeAll(assignmentsToDelete);
    }

    @Override
    public String toString() {
        return super.toString() + "(embauché le " +
                hireDate.get(Calendar.DAY_OF_MONTH) +
                " " + hireDate.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.FRANCE) +
                " " + hireDate.get(Calendar.YEAR) +
                ")";
    }
}
