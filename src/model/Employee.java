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
            throws StreetException, EmailException, PasswordException, GenderException, PhoneException, NameException,
            DiscountException, DateException, FirstNameException {
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
            throws StreetException, EmailException, PasswordException, GenderException, PhoneException, NameException,
            DiscountException, DateException, FirstNameException {
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
            throws EmailException, DiscountException, PasswordException, GenderException, NameException, PhoneException,
            StreetException, DateException, FirstNameException {
        this(password, lastName, firstName, secondName, maidenName, birthDate, streetName, locality, email, phoneNbr,
                gender, hireDate, endContractDate, isEmployeeOfMonth, discount, manager, Boolean.FALSE);
    }


    public void setDiscount(Double discount) throws DiscountException {
        if(discount < 0 || discount > 100)
            throw new DiscountException(discount);
        this.discount = discount;
    }

    public void setEndContractDate(GregorianCalendar endContractDate) throws DateException {
        if(endContractDate != null){
            if(endContractDate.before(hireDate))
                throw new DateException(endContractDate, hireDate);
            this.endContractDate = endContractDate;
        }
    }

    public void addAssignments(ArrayList<Assignment> newAssignments) {
        assignments.addAll(newAssignments);
    }

    public void removeAssignments(ArrayList<Assignment> assignmentsToDelete) {
        assignments.removeAll(assignmentsToDelete);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(super.toString());

        res.append("(embauché le ");
        res.append(hireDate.get(Calendar.DAY_OF_MONTH));
        res.append(" ").append(hireDate.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.FRANCE));
        res.append(" ").append(hireDate.get(Calendar.YEAR));
        res.append(")");
        return res.toString();
    }
}
