package userInterface.tableModels;

import model.Advantage;
import model.exceptions.AllDataException;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class AllAdvantagesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Advantage> contents;

    public AllAdvantagesModel(ArrayList<Advantage> advantages) throws AllDataException {
        columnNames = new ArrayList<>();
        columnNames.add("Label");
        columnNames.add("Remise");
        columnNames.add("Date de début");
        columnNames.add("Date de fin");
        columnNames.add("Point requis");
        setContents(advantages);
    }

    private void setContents(ArrayList<Advantage> contents) throws AllDataException {
        if(contents.isEmpty())
            throw new AllDataException("la récupération des clients", null);
        this.contents = contents;
    }

    @Override
    public int getColumnCount() { return columnNames.size(); }
    @Override
    public int getRowCount() { return contents.size(); }
    @Override
    public  String getColumnName(int column) { return columnNames.get(column); }

    public Object getValueAt (int row, int column) {
        Advantage advantage = contents.get(row);
        return switch(column) {
            case 0 -> advantage.getLabel();
            case 1 -> advantage.getDiscount();
            case 2 -> advantage.getStartDate().getTime();
            case 3 -> advantage.getEndDate().getTime();
            case 4 -> advantage.getPointsRequired();
            default -> null;
        };
    }

    public Class getColumnClass (int column) {
        Class c;
        c = switch (column) {
            case 0 -> String.class;
            case 1 -> Double.class;
            case 2 -> Date.class;
            case 3 -> Date.class;
            case 4 -> Integer.class;
            default -> String.class;
        };
        return c;
    }



}
