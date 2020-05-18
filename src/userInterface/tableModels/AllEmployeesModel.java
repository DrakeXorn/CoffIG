package userInterface.tableModels;

import model.Employee;
import model.exceptions.AllDataException;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class AllEmployeesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Employee> contents;

    public AllEmployeesModel(ArrayList<Employee> employees) throws AllDataException {
        columnNames = new ArrayList<>();
        columnNames.add("Identifiant");
        columnNames.add("Nom");
        columnNames.add("Prénom");
        columnNames.add("Second prénom");
        columnNames.add("Nom de jeune fille");
        columnNames.add("Date de naissance");
        columnNames.add("Adresse");
        columnNames.add("Localité");
        columnNames.add("Email");
        columnNames.add("Téléphone");
        columnNames.add("Sexe");
        columnNames.add("Date d'embauche");
        columnNames.add("Date de fin de contra");
        columnNames.add("Est l'employé du mois");
        columnNames.add("Remise atribuée");
        columnNames.add("Numéro de place de parking");
        columnNames.add("Manager");
        setContents(employees);
    }

    public void setContents(ArrayList<Employee> contents) throws AllDataException {
        if(contents.isEmpty())
            throw new AllDataException("la récupération des employees", null);
        this.contents = contents;
    }

    @Override
    public int getColumnCount( ) { return columnNames.size( ); }
    @Override
    public int getRowCount( ) { return contents.size( ); }
    @Override
    public String getColumnName(int column) { return columnNames.get(column); }

    public Object getValueAt (int row, int column) {
        Employee employee = contents.get(row);
        return switch (column) {
            case 0 -> employee.getUserID();
            case 1 -> employee.getLastName();
            case 2 -> employee.getFirstName();
            case 3 -> employee.getSecondName();
            case 4 -> employee.getMaidenName();
            case 5 -> employee.getBirthDate().getTime();
            case 6 -> employee.getStreetName();
            case 7 -> employee.getLocality();
            case 8 -> employee.getEmail();
            case 9 -> employee.getPhone();
            case 10 -> employee.getGender();
            case 11 -> employee.getHireDate();
            case 12 -> employee.getEndContractDate() != null ? employee.getEndContractDate() : null;
            case 13 -> employee.getEmployeeOfMonth();
            case 14 -> employee.getDiscount();
            case 15 -> employee.getParkingSpaceNumber() != null ? employee.getParkingSpaceNumber() : null;
            case 16 -> employee.getManager() != null ? employee.getManager().getLastName() : null;
            default -> null;
        };
    }

    public Class getColumnClass (int column) {
        Class c;
        c = switch (column) {
            case 0 -> String.class; // id
            case 1 -> String.class; // last name
            case 2 -> String.class; // first name
            case 3 -> String.class; // second name
            case 4 -> String.class; // maiden name
            case 5 -> Date.class;   // birth date
            case 6 -> String.class; // street
            case 7 -> String.class; // locality
            case 8 -> String.class; // email
            case 9 -> String.class; // phone
            case 10 -> Character.class; // gender
            case 11 -> Date.class; // hire date
            case 12 -> Date.class; // end date
            case 13 -> Boolean.class; // isEmployeeOfTheMonth
            case 14 -> Double.class;  // Discount
            case 15 -> Integer.class; // getParkingSpaceNumber
            case 16 -> String.class;  // LastName of Manager
            default -> String.class;
        };
        return c;
    }
}
