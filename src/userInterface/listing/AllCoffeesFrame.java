package userInterface.listing;

import controller.CoffeeController;

import javax.swing.*;
import java.awt.*;

public class AllCoffeesFrame extends JFrame {
    private JScrollPane scrollPane;
    private JTable coffeeTable;
    private CoffeeController controller;
    private Container container;

    public AllCoffeesFrame() {
        super("Tous les cafés");
        this.setBounds(50, 50, 1400, 400);

        container = getContentPane();
        controller = new CoffeeController();

        try {
            coffeeTable = new JTable(new AllCoffeesModel(controller.getAllCoffees()));
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
            scrollPane = new JScrollPane(coffeeTable);

            container.add(scrollPane);
            setVisible(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
