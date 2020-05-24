package userInterface.userRegistration;

import controller.CustomerController;
import model.Customer;
import userInterface.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UpdateCustomersFrame extends JFrame {
    private UpdateCustomersModel model;
    private JTable customerTable;
    private JScrollPane scrollPane;
    private ListSelectionModel listSelect;
    private ButtonsUpdateDeleteCustomerPanel buttonsPanel;
    private MainWindow mainWindow;
    private Container container;

    public UpdateCustomersFrame(MainWindow window) {
        super("Modifier un client");
        this.setBounds(90, 90, 800, 400);
        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        mainWindow = window;
        CustomerController controller = new CustomerController();

        try {
            model = new UpdateCustomersModel(controller.getAllCustomers());
            customerTable = new JTable(model);
            customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            customerTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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

    public ArrayList<Customer> getChosenCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        if (listSelect.getMinSelectionIndex() != -1) {
            for (int index : listSelect.getSelectedIndices())
                customers.add(model.getRow(index));
        }

        return customers;
    }
}
