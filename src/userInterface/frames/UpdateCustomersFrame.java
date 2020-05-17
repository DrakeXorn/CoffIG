package userInterface.frames;

import controller.CustomerController;
import model.Customer;
import userInterface.panels.ButtonsUpdateUserForm;
import userInterface.panels.CustomerForm;
import userInterface.tableModels.UpdateCustomersModel;
import userInterface.panels.UserForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCustomersFrame extends JFrame {
    private UpdateCustomersModel model;
    private JTable customerTable;
    private JScrollPane scrollPane;
    private ListSelectionModel listSelect;
    private JButton modify;
    private CustomerController controller;
    private MainWindow mainWindow;
    private Container container;

    public UpdateCustomersFrame(MainWindow window){
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

            modify = new JButton("Modifier un client");
            container.add(modify, BorderLayout.SOUTH);
            modify.addActionListener(new ModifyListener());

            setVisible(true);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ModifyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(!listSelect.isSelectionEmpty()){
                Customer customerToModify = model.getRow(listSelect.getMinSelectionIndex());
                mainWindow.getWindowContainer().removeAll();
                UserForm userForm = new UserForm(customerToModify);
                CustomerForm customerForm = new CustomerForm(userForm, customerToModify);
                mainWindow.getWindowContainer().add(userForm, BorderLayout.NORTH);
                mainWindow.getWindowContainer().add(customerForm, BorderLayout.CENTER);
                mainWindow.getWindowContainer().add(new ButtonsUpdateUserForm(mainWindow, customerForm), BorderLayout.SOUTH);
                mainWindow.getWindowContainer().repaint();
                mainWindow.setVisible(true);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Sélectionnez un client à modifier !",
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
