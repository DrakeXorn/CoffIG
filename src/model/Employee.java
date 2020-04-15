package model;

import java.util.GregorianCalendar;
import java.util.Locale;

public class Employee extends User {
    private GregorianCalendar hireDate;
    private GregorianCalendar endContractDate;
    private Boolean isEmployeeOfMonth;
    private Double discount;
    private Integer parkingSpaceNumber;
    private Employee manager;

    public Employee(Integer userID, String password, String lastName, String firstName, String secondName,
                    String maidenName, GregorianCalendar birthDate, String streetName, String email, String phoneNbr,
                    Character gender, Locality locality, GregorianCalendar hireDate, GregorianCalendar endContractDate, Boolean isEmployeeOfMonth,
                    Double discount, Integer parkingSpaceNumber, Employee manager) {
        super(userID, password, lastName, firstName, secondName, maidenName, birthDate, streetName, email, phoneNbr,
                gender, locality);
        this.hireDate = hireDate;
        this.endContractDate = endContractDate;

        this.isEmployeeOfMonth = isEmployeeOfMonth;
        this.discount = discount;
        this.parkingSpaceNumber = parkingSpaceNumber;
        
        if (manager != null) {
            this.manager = manager;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(super.toString());

        res.append("(embauché le ");
        res.append(hireDate.getDisplayName(GregorianCalendar.DAY_OF_MONTH, GregorianCalendar.LONG, Locale.FRANCE));
        res.append(" ").append(hireDate.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.FRANCE));
        res.append(" ").append(hireDate.getDisplayName(GregorianCalendar.YEAR, GregorianCalendar.LONG, Locale.FRANCE));
        res.append(")");

        return res.toString();
    }
}
