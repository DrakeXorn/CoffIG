package userInterface;

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

    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
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
        Class c;
        switch (column) {
            case 0: c = String.class; break; // id
            case 1: c = String.class; break; // last name
            case 2: c = String.class; break;// first name
            case 3: c = String.class; break; // second name
            case 4: c = String.class; break; // maiden name
            case 5: c = Date.class; break; // birth date
            case 6: c = String.class; break; // street
            case 7: c = String.class; break; // locality
            case 8: c = String.class; break; // email
            case 9: c = String.class; break; // phone
            case 10: c = Character.class; break; // gender
            case 11: c = Boolean.class; break; // wants advertising
            case 12: c = Integer.class; break; // degree
            case 13: c = String.class; break; // loyalty card
            default: c = String.class;
        }
        return c;
    }

}
