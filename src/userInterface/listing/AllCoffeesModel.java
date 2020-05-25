package userInterface.listing;

import model.Coffee;
import model.exceptions.AllDataException;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllCoffeesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Coffee> contents;

    public AllCoffeesModel(ArrayList<Coffee> coffees) throws AllDataException {
        contents = new ArrayList<>();
        columnNames = new ArrayList<>();
        columnNames.add("Label");
        columnNames.add("Origine");
        columnNames.add("Intensité");
        columnNames.add("Découverte");
        columnNames.add("Poids requis");
        columnNames.add("Prix");
        columnNames.add("Quantité initiale (kg)");
        columnNames.add("Moment conseillé");
        columnNames.add("En grains ?");
        columnNames.add("Écologique ?");
        columnNames.add("Allée");
        columnNames.add("Étagère");
        columnNames.add("Emplacement");
        setContents(coffees);
    }

    public void setContents(ArrayList<Coffee> coffees) throws AllDataException {
        if (coffees.isEmpty())
            throw new AllDataException("cafés", null);
        this.contents = coffees;
    }

    @Override
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
        return switch (columnIndex) {
            case 0 -> coffee.getLabel();
            case 1 -> coffee.getOriginCountry();
            case 2 -> coffee.getIntensity();
            case 3 -> coffee.getDiscoveryYear();
            case 4 -> coffee.getWeightNeededForPreparation();
            case 5 -> coffee.getPrice();
            case 6 -> coffee.getPackaging();
            case 7 -> coffee.getRecommendedConsumingMoment();
            case 8 -> coffee.isInGrains();
            case 9 -> coffee.isEnvironmentFriendly();
            case 10 -> coffee.getStockLocation().getAlley();
            case 11 -> coffee.getStockLocation().getShelf();
            case 12 -> coffee.getStockLocation().getNumber();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return switch (column) {
            case 2, 3, 10, 11, 12 -> Integer.class;
            case 4, 5, 6 -> Double.class;
            case 8, 9 -> Boolean.class;
            default -> String.class;
        };
    }

    public Coffee getRow(int row) {
        return contents.get(row);
    }
}
