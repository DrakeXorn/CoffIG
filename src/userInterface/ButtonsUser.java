package userInterface;

import controller.CustomerController;
import model.*;
import model.exceptions.AddCustomerException;
import model.exceptions.ConnectionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonsUser extends JPanel {
    private JLabel requiredFields;
    private JButton validation, reset;
    private MainWindow parent;
    private User user;
    private JPanel form;
    private CustomerController controller;

    public ButtonsUser(MainWindow mainWindow, JPanel form){
        this.parent = mainWindow;
        this.form = form;
        controller = new CustomerController();
        this.setLayout(new FlowLayout());

        requiredFields = new JLabel("*champs obligatoires");
        this.add(requiredFields);

        validation = new JButton("Validation");
        this.add(validation);
        reset = new JButton("Réinitialisation");
        this.add(reset);

        validation.addActionListener(new ValidationListener());
        reset.addActionListener(new ResetListener());
    }

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(form instanceof NewCustomerForm){
                user = ((NewCustomerForm)form).createCustomer();
                try {
                    controller.addCustomer((Customer)user);
                } catch (AddCustomerException | ConnectionException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(),
                            "Erreur !", JOptionPane.INFORMATION_MESSAGE);
                }
            }else
                user = ((NewEmployeeForm)form).createEmployee();
            //JOptionPane.showMessageDialog(null, user , "Validation de l'inscription", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            parent.getWindowContainer().removeAll();
            NewUserForm user = new NewUserForm();
            parent.getWindowContainer().add(user, BorderLayout.NORTH);

            if (form instanceof NewCustomerForm){
                NewCustomerForm customer = new NewCustomerForm(user);
                parent.getWindowContainer().add(customer, BorderLayout.CENTER);
                parent.getWindowContainer().add(new ButtonsUser(parent, customer), BorderLayout.SOUTH);
            }
            else {
                NewEmployeeForm employee = new NewEmployeeForm(user);
                parent.getWindowContainer().add(employee, BorderLayout.CENTER);
                parent.getWindowContainer().add(new ButtonsUser(parent, employee), BorderLayout.SOUTH);
            }
            parent.getWindowContainer().repaint();
            parent.setVisible(true);
        }
    }
}
