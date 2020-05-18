package userInterface.research.service;

import model.Assignment;

import javax.swing.table.AbstractTableModel;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class AssignmentsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Assignment> contents;

    public AssignmentsModel(ArrayList<Assignment> assignments) {
        contents = new ArrayList<>();
        columnNames = new ArrayList<>();
        columnNames.add("Date");
        columnNames.add("Heure de début");
        columnNames.add("Heure de fin");
        contents.addAll(assignments);
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
        Assignment assignment = contents.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> assignment.getServiceDate().getTime();
            case 1 -> assignment.getService().getStartTime();
            case 2 -> assignment.getService().getEndTime();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return column == 0 ? Date.class : LocalTime.class;
    }
}
