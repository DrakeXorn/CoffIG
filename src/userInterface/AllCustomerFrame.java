package userInterface;

import controller.CustomerController;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllCustomerFrame extends JFrame {
    private AllCustomersModel model;
    private JTable customerTable;
    private JScrollPane scrollPane;
    private ListSelectionModel listSelect;
    private JButton modify, delete;
    private CustomerController controller;
    private MainWindow mainWindow;
    private Container container;

    public AllCustomerFrame(MainWindow window, Boolean isOnlyDisplay){
        super("Affichage de tous les clients");
        this.setBounds(100, 50, 1500, 600);
        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        mainWindow = window;
        controller = new CustomerController();

        try {
            model = new AllCustomersModel(controller.getAllCustomers());
            customerTable = new JTable(model);
            customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = customerTable.getSelectionModel();

            scrollPane = new JScrollPane((customerTable));
            container.add(scrollPane, BorderLayout.CENTER);

            if(!isOnlyDisplay){
                modify = new JButton("Modifier un client");
                container.add(modify, BorderLayout.SOUTH);
                modify.addActionListener(new ModifyListener());
            }
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
                UserForm user = new UserForm(customerToModify);
                CustomerForm customerForm = new CustomerForm(user, customerToModify);
                mainWindow.getWindowContainer().add(user, BorderLayout.NORTH);
                mainWindow.getWindowContainer().add(customerForm, BorderLayout.CENTER);
                mainWindow.getWindowContainer().add(new ButtonsModifyUserForm(mainWindow, customerForm), BorderLayout.SOUTH);
                mainWindow.getWindowContainer().repaint();
                mainWindow.setVisible(true);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Sélectionnez un client à modifier ou supprimer !",
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
