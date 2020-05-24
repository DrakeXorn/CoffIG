package userInterface.listing;

import controller.CustomerController;
import userInterface.MainWindow;

import javax.swing.*;
import java.awt.*;

public class AllCustomersFrame extends JFrame {
    private AllCustomersModel model;
    private JTable customerTable;
    private JScrollPane scrollPane;
    private CustomerController controller;
    private Container container;

    public AllCustomersFrame(){
        super("Affichage de tous les clients");
        this.setBounds(50, 50, 1400, 400);
        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        controller = new CustomerController();

        try {
            model = new AllCustomersModel(controller.getAllCustomers());
            customerTable = new JTable(model);
            customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            scrollPane = new JScrollPane((customerTable));
            container.add(scrollPane, BorderLayout.CENTER);
            setVisible(true);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
