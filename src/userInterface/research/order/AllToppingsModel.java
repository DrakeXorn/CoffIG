package userInterface.research.order;

import model.Topping;
import model.exceptions.AllDataException;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllToppingsModel extends AbstractTableModel  {
    private ArrayList<String> columnNames;
    private ArrayList<Topping> contents;

    public AllToppingsModel(ArrayList<Topping> toppings) throws AllDataException {
        columnNames = new ArrayList<>();
        columnNames.add("Identifiant");
        columnNames.add("Label");
        columnNames.add("Prix");
        setContents(toppings);
    }

    public void setContents(ArrayList<Topping> contents) throws AllDataException {
        if(contents.isEmpty())
            throw new AllDataException("la récupération des toppings", null);
        this.contents = contents;
    }

    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) { return columnNames.get(column); }

    public Topping getRow(int row){
        return contents.get(row);
    }

    public Object getValueAt (int row, int column) {
        Topping topping = contents.get(row);
        return switch(column) {
            case 0 -> topping.getToppingID();
            case 1 -> topping.getLabel();
            case 2 -> topping.getPrice();
            default -> null;
        };
    }

    public Class<?> getColumnClass (int column)
    {
        return switch (column) {
            case 0 -> Integer.class; // id
            case 1 -> String .class;  // label
            case 2 -> Double.class; // prix
            default -> String.class;
        };
    }
}
