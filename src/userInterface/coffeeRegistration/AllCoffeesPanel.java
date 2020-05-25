package userInterface.coffeeRegistration;

import controller.CoffeeController;
import model.Coffee;
import userInterface.listing.AllCoffeesModel;

import javax.swing.*;
import java.awt.*;

public class AllCoffeesPanel extends JPanel {
    private JScrollPane scrollPane;
    private AllCoffeesModel coffeeTableModel;
    private JTable coffeeTable;
    private CoffeeController controller;

    public AllCoffeesPanel() {
        controller = new CoffeeController();

        try {
            coffeeTableModel = new AllCoffeesModel(controller.getAllCoffees());
            coffeeTable = new JTable(coffeeTableModel);
            coffeeTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            coffeeTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            coffeeTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            coffeeTable.getColumnModel().getColumn(5).setPreferredWidth(20);
            coffeeTable.getColumnModel().getColumn(6).setPreferredWidth(90);
            coffeeTable.getColumnModel().getColumn(8).setPreferredWidth(40);
            coffeeTable.getColumnModel().getColumn(9).setPreferredWidth(40);
            coffeeTable.getColumnModel().getColumn(10).setPreferredWidth(30);
            coffeeTable.getColumnModel().getColumn(11).setPreferredWidth(30);
            coffeeTable.getColumnModel().getColumn(12).setPreferredWidth(50);

            coffeeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            coffeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setLayout(new BorderLayout());
            scrollPane = new JScrollPane(coffeeTable);
            add(scrollPane, BorderLayout.CENTER);

            setVisible(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Coffee getChosenCoffee() {
        return coffeeTable.getSelectionModel().getMinSelectionIndex() != -1 ? coffeeTableModel.getRow(coffeeTable.getSelectedRow()) : null;
    }
}
