package userInterface.tableModels;

import model.Customer;
import model.exceptions.AllDataException;
import javax.swing.table.*;
import java.util.*;

public class AllCustomersModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Customer> contents;

    public AllCustomersModel(ArrayList<Customer> customers) throws AllDataException {
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
        columnNames.add("Reçoit la newsletter");
        columnNames.add("Degré de satisfaction");
        columnNames.add("N° carte de fidélité");
        setContents(customers);
    }

    public void setContents(ArrayList<Customer> contents) throws AllDataException {
        if(contents.isEmpty())
            throw new AllDataException("la récupération des clients", null);
        this.contents = contents;
    }

    @Override
    public int getColumnCount( ) { return columnNames.size( ); }
    @Override
    public int getRowCount( ) { return contents.size( ); }
    @Override
    public String getColumnName(int column) { return columnNames.get(column); }

    public Customer getRow(int row){
        return contents.get(row);
    }

    public Object getValueAt (int row, int column) {
        Customer customer = contents.get(row);
        return switch (column) {
            case 0 -> customer.getUserID();
            case 1 -> customer.getLastName();
            case 2 -> customer.getFirstName();
            case 3 -> customer.getSecondName();
            case 4 -> customer.getMaidenName();
            case 5 -> customer.getBirthDate().getTime();
            case 6 -> customer.getStreetName();
            case 7 -> customer.getLocality();
            case 8 -> customer.getEmail();
            case 9 -> customer.getPhone();
            case 10 -> customer.getGender();
            case 11 -> customer.getWantsAdvertising();
            case 12 -> customer.getSatisfactionDegree();
            case 13 -> customer.getLoyaltyCard() != null ? customer.getLoyaltyCard().getLoyaltyCardID() : null;
            default -> null;
        };
    }

    public Class getColumnClass (int column) {
        return switch (column) {
            case 0, 1, 2, 3, 4, 6, 7, 8, 9, 13 -> String.class;
            // id, last name, first name, second name, maiden name, street, locality, email, phone, loyalty card
            case 5 -> Date.class; // birth date
            case 10 -> Character.class; // gender
            case 11 -> Boolean.class; // wants advertising
            case 12 -> Integer.class; // degree
            default -> String.class;
        };
    }
}
