package model;

import model.exceptions.*;

import java.util.*;

public class Employee extends User {
    private GregorianCalendar hireDate;
    private GregorianCalendar endContractDate;
    private Boolean isEmployeeOfMonth;
    private Double discount;
    private Integer parkingSpaceNumber;
    private Employee manager;

    // pour la création d'un employé
    public Employee(Integer userID, String password, String lastName, String firstName, String secondName, String maidenName,
                    GregorianCalendar birthDate, String streetName, Locality locality, String email, String phone, Character gender,
                    GregorianCalendar hireDate, GregorianCalendar endContractDate, Boolean isEmployeeOfMonth, Double discount, Integer parkingSpaceNumber, Employee manager)
            throws StringInputException, DateException, CharacterInputException {
        super(userID, password, lastName, firstName, secondName, maidenName, birthDate, streetName, locality, email, phone, gender);
       this.hireDate = hireDate;
       this.endContractDate = endContractDate;
       this.isEmployeeOfMonth = isEmployeeOfMonth;
       this.discount = discount;
       this.parkingSpaceNumber = parkingSpaceNumber;
       this.manager = manager;
    }

    // pour la récupération de la BD
    public Employee(Integer userID, String password, String lastName, String firstName,
                    GregorianCalendar birthDate, String streetName, Locality locality, String email, String phone, Character gender,
                    GregorianCalendar hireDate, Boolean isEmployeeOfMonth, Double discount)
            throws DateException, StringInputException, CharacterInputException {
        this(userID, password, lastName, firstName, null, null, birthDate, streetName, locality, email, phone,
                gender, hireDate, null, isEmployeeOfMonth, discount, null, null);
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

    public Integer getParkingSpaceNumber() {
        return parkingSpaceNumber;
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
        if (endContractDate != null){
            if (endContractDate.before(hireDate))
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

    @Override
    public String description() {
        return super.description() + "(embauché le " +
                hireDate.get(Calendar.DAY_OF_MONTH) +
                " " + hireDate.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.FRANCE) +
                " " + hireDate.get(Calendar.YEAR) +
                ")";
    }
}
