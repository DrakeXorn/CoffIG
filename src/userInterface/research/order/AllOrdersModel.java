package userInterface.research.order;

import model.Order;
import model.exceptions.AllDataException;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class AllOrdersModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Order> contents;

    public AllOrdersModel(ArrayList<Order> orders) throws AllDataException {
        columnNames = new ArrayList<>();
        columnNames.add("Numéro");
        columnNames.add("Date");
        columnNames.add("A emporter");
        columnNames.add("Prix total");
        setContents(orders);
    }

    public void setContents(ArrayList<Order> contents) throws AllDataException {
        if(contents.isEmpty())
            throw new AllDataException("la récupération des commandes", null);
        this.contents = contents;
    }

    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) { return columnNames.get(column); }

    public Order getRow(int row){
        return contents.get(row);
    }

    public Object getValueAt (int row, int column) {
        Order order = contents.get(row);
        return switch(column) {
            case 0 -> order.getOrderNumber();
            case 1 -> order.getDate().getTime();
            case 2 -> order.isToTakeAway();
            case 3 -> order.getPrice();
            default -> null;
        };
    }

    public Class<?> getColumnClass (int column)
    {
        return switch (column) {
            case 0 -> Integer.class; // id
            case 1 -> Date.class;  // date
            case 2 -> Boolean.class; // isToTakeAway
            case 3 -> Double.class; // price
            default -> String.class;
        };
    }
}
