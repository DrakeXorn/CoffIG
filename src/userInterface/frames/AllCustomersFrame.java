package userInterface.frames;

import controller.CustomerController;
import userInterface.tableModels.AllCustomersModel;

import javax.swing.*;
import java.awt.*;

public class AllCustomersFrame extends JFrame {
    private AllCustomersModel model;
    private JTable customerTable;
    private JScrollPane scrollPane;
    private ListSelectionModel listSelect;
    private JButton modify, delete;
    private CustomerController controller;
    private MainWindow mainWindow;
    private Container container;

    public AllCustomersFrame(MainWindow window){
        super("Affichage de tous les clients");
        this.setBounds(50, 50, 1400, 400);
        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        mainWindow = window;
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
