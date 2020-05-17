package userInterface.panels;

import controller.CustomerController;
import model.Customer;
import userInterface.frames.UpdateCustomersFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsUpdateDeleteCustomerPanel extends JPanel {
    private JButton updateButton;
    private JButton deleteButton;
    private UpdateCustomersFrame parent;

    public ButtonsUpdateDeleteCustomerPanel(UpdateCustomersFrame parent) {
        this.parent = parent;

        deleteButton = new JButton("Supprimer le client");
        deleteButton.addActionListener(new DeleteButtonListener());
        add(deleteButton);

        updateButton = new JButton("Modifier le client");
        updateButton.addActionListener(new UpdateButtonListener());
        add(updateButton);
    }

    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            Customer chosenCustomer = parent.getChosenCustomer();

            if (chosenCustomer != null) {
                UserForm userForm = new UserForm(chosenCustomer);
                CustomerForm customerForm = new CustomerForm(new UserForm(chosenCustomer), chosenCustomer);

                parent.getParent().resetSize();
                parent.getParent().getWindowContainer().removeAll();
                parent.getParent().getWindowContainer().add(userForm, BorderLayout.NORTH);
                parent.getParent().getWindowContainer().add(customerForm, BorderLayout.CENTER);
                parent.getParent().getWindowContainer().add(new ButtonsModifyUserForm(parent.getParent(), customerForm), BorderLayout.SOUTH);
                parent.getParent().getWindowContainer().repaint();
                parent.getParent().setVisible(true);
                setVisible(false);
            } else
                JOptionPane.showMessageDialog(null, "Sélectionnez un café à modifier !",
                        "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Customer customer = parent.getChosenCustomer();

            if (customer != null) {
                if (JOptionPane.showConfirmDialog(parent, "Êtes-vous sûr de vouloir supprimer " + customer.getIdentity() + " ?", "Confirmer la suppression", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    try {
                        CustomerController coffeeController = new CustomerController();

                        coffeeController.removeCustomer(customer);
                        parent.dispose();
                        parent = new UpdateCustomersFrame(parent.getParent());
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(parent, exception.getMessage(), "Erreur lors de la suppression", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else
                JOptionPane.showMessageDialog(null, "Sélectionnez un café à supprimer !",
                        "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
