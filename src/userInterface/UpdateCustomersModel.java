package userInterface;

import model.Customer;
import model.exceptions.AllDataException;
import javax.swing.table.*;
import java.util.*;

public class UpdateCustomersModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<Customer> contents;

    public UpdateCustomersModel(ArrayList<Customer> customers) throws AllDataException {
        columnNames = new ArrayList<>();
        columnNames.add("Identifiant");
        columnNames.add("Nom");
        columnNames.add("Prénom");
        columnNames.add("Date de naissance");
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
        return switch(column) {
            case 0 -> customer.getUserID();
            case 1 -> customer.getLastName();
            case 2 -> customer.getFirstName();
            case 3 -> customer.getBirthDate().getTime();
            default -> null;
        };
    }

    public Class getColumnClass (int column) {
        return switch (column) {
            case 0, 1, 2 -> String.class; // id, last name, first name
            case 3 -> Date.class;  // birth date
            default -> String.class;
        };
    }

}
