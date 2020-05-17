package userInterface.frames;

import controller.CoffeeController;
import userInterface.tableModels.AllCoffeesModel;

import javax.swing.*;
import java.awt.*;

public class AllCoffeesFrame extends JFrame {
    private JScrollPane scrollPane;
    private JTable coffeeTable;
    private ListSelectionModel listSelect;
    private CoffeeController controller;
    private Container container;
    private MainWindow parent;

    public AllCoffeesFrame() {
        super("Tous les cafés");

        setBounds(50, 200, 1250, 600);
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
            listSelect = coffeeTable.getSelectionModel();
            scrollPane = new JScrollPane(coffeeTable);

            add(scrollPane);
            setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
