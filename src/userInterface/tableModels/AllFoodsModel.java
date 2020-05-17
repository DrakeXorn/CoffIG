package userInterface.tableModels;

import model.FoodOrdering;
import model.exceptions.AllDataException;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllFoodsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<FoodOrdering> contents;

    public AllFoodsModel(ArrayList<FoodOrdering> foods) throws AllDataException {
        columnNames = new ArrayList<>();
        columnNames.add("Label");
        columnNames.add("Quantité");
        columnNames.add("Prix");
        setContents(foods);
    }

    public void setContents(ArrayList<FoodOrdering> contents) throws AllDataException {
        if(contents.isEmpty())
            throw new AllDataException("la récupération de la nourriture", null);
        this.contents = contents;
    }

    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) { return columnNames.get(column); }

    public FoodOrdering getRow(int row){
        return contents.get(row);
    }

    public Object getValueAt (int row, int column) {
        FoodOrdering foodOrdering = contents.get(row);
        return switch(column) {
            case 0 -> foodOrdering.getFood().getLabel();
            case 1 -> foodOrdering.getNbrPieces();
            case 2 -> foodOrdering.getSellingPrice();
            default -> null;
        };
    }

    public Class<?> getColumnClass (int column)
    {
        return switch (column) {
            case 0 -> String.class; // label
            case 1 -> Integer.class; // quantité
            case 2 -> Double.class; // prix
            default -> String.class;
        };
    }
}
