package userInterface.userRegistration;

import controller.CustomerController;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonsUpdateDeleteCustomerPanel extends JPanel {
    private JButton updateButton;
    private JButton deleteButton;
    private UpdateCustomersFrame parent;

    public ButtonsUpdateDeleteCustomerPanel(UpdateCustomersFrame parent) {
        this.parent = parent;

        updateButton = new JButton("Modifier le client");
        updateButton.addActionListener(new UpdateButtonListener());
        add(updateButton);

        deleteButton = new JButton("Supprimer le client");
        deleteButton.addActionListener(new DeleteButtonListener());
        add(deleteButton);
    }

    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            ArrayList<Customer> chosenCustomers = parent.getChosenCustomers();

            if (chosenCustomers.size() == 1) {
                UserForm userForm = new UserForm(chosenCustomers.get(0));
                CustomerForm customerForm = new CustomerForm(userForm, chosenCustomers.get(0));

                parent.getParent().resetSize();
                parent.getParent().getWindowContainer().removeAll();
                parent.getParent().getWindowContainer().add(userForm, BorderLayout.NORTH);
                parent.getParent().getWindowContainer().add(customerForm, BorderLayout.CENTER);
                parent.getParent().getWindowContainer().add(new ButtonsUpdateUserForm(parent.getParent(), customerForm), BorderLayout.SOUTH);
                parent.getParent().getWindowContainer().repaint();
                parent.getParent().setVisible(true);
                parent.dispose();
            } else
                JOptionPane.showMessageDialog(parent, chosenCustomers.size() == 0 ? "Sélectionnez un client à modifier !" : "Vous ne pouvez choisir qu'un seul client à modifier !",
                        "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Customer> customers = parent.getChosenCustomers();

            if (customers.size() != 0) {
                for (Customer customer : customers) {
                    if (JOptionPane.showConfirmDialog(parent, "Êtes-vous sûr de vouloir supprimer " + customer.getIdentity() + " ?", "Confirmer la suppression", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        try {
                            CustomerController controller = new CustomerController();

                            controller.removeCustomer(customer);
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(parent, exception.getMessage(), "Erreur lors de la suppression", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                parent.dispose();
                parent = new UpdateCustomersFrame(parent.getParent());
            } else
                JOptionPane.showMessageDialog(parent, "Sélectionnez un (des) client(s) à supprimer !",
                        "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
