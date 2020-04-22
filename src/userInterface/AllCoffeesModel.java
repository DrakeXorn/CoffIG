package userInterface;

import model.Coffee;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;

public class AllCoffeesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Coffee> contents;

    public AllCoffeesModel(ArrayList<Coffee> coffees) {
        columnNames = new ArrayList<>();
        columnNames.add("Label");
        columnNames.add("Pays d'origine");
        columnNames.add("Intensité");
        columnNames.add("Date de découverte");
        columnNames.add("Poids requis pour la préparation");
        columnNames.add("Prix");
        columnNames.add("Quantité initiale dans un paquet");
        columnNames.add("Date de découverte");
        columnNames.add("Moment de la journée préféré");
        columnNames.add("Est en grains");
        columnNames.add("Est cultivé écologiquement");
        columnNames.add("Numéro de l'allée");
        columnNames.add("Numéro de l'étagère");
        columnNames.add("Numéro d'emplacement");
        contents.addAll(coffees);
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public int getRowCount() {
        return contents.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Coffee coffee = contents.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return coffee.getLabel();
            //TODO à terminer
            default:
                return null;
        }
    }
}
