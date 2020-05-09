package userInterface;

import model.DrinkOrdering;
import model.exceptions.AllDataException;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllDrinksModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<DrinkOrdering> contents;

    public AllDrinksModel(ArrayList<DrinkOrdering> drinks) throws AllDataException {
        columnNames = new ArrayList<>();
        columnNames.add("Label");
        columnNames.add("Est glacé");
        columnNames.add("Taille");
        columnNames.add("Quantité");
        columnNames.add("Prix");
        setContents(drinks);
    }

    public void setContents(ArrayList<DrinkOrdering> contents) throws AllDataException {
        if(contents.isEmpty())
            throw new AllDataException("la récupération des boissons", null);
        this.contents = contents;
    }

    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) { return columnNames.get(column); }

    public DrinkOrdering getRow(int row){
        return contents.get(row);
    }

    public Object getValueAt (int row, int column) {
        DrinkOrdering drinkOrdering = contents.get(row);
        return switch(column) {
            case 0 -> drinkOrdering.getDrink().getLabel();
            case 1 -> drinkOrdering.getDrink().getCold();
            case 2 -> drinkOrdering.getSize();
            case 3 -> drinkOrdering.getNbrPieces();
            case 4 -> drinkOrdering.getSellingPrice();
            default -> null;
        };
    }

    public Class getColumnClass (int column)
    {
        return switch (column) {
            case 0, 2 -> String.class; // label, size
            case 1 -> Boolean.class;  // cold
            case 3 -> Integer.class; // quantité
            case 4 -> Double.class; // prix
            default -> String.class;
        };
    }
}
