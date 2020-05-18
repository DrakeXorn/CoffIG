package userInterface.userRegistration;

import controller.CustomerController;
import model.Customer;
import userInterface.MainWindow;

import javax.swing.*;
import java.awt.*;

public class UpdateCustomersFrame extends JFrame {
    private UpdateCustomersModel model;
    private JTable customerTable;
    private JScrollPane scrollPane;
    private ListSelectionModel listSelect;
    private ButtonsUpdateDeleteCustomerPanel buttonsPanel;
    private CustomerController controller;
    private MainWindow mainWindow;
    private Container container;

    public UpdateCustomersFrame(MainWindow window) {
        super("Modifier un client");
        this.setBounds(90, 90, 800, 400);
        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        mainWindow = window;
        controller = new CustomerController();

        try {
            model = new UpdateCustomersModel(controller.getAllCustomers());
            customerTable = new JTable(model);
            customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = customerTable.getSelectionModel();

            scrollPane = new JScrollPane((customerTable));
            container.add(scrollPane, BorderLayout.CENTER);

            buttonsPanel = new ButtonsUpdateDeleteCustomerPanel(this);
            container.add(buttonsPanel, BorderLayout.SOUTH);

            setVisible(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public MainWindow getParent() {
        return mainWindow;
    }

    public Customer getChosenCustomer() {
        return customerTable.getSelectionModel().getMinSelectionIndex() != -1 ? model.getRow(customerTable.getSelectedRow()) : null;
    }
}
