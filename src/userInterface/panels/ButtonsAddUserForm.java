package userInterface.panels;

import controller.CustomerController;
import model.*;
import model.exceptions.*;
import userInterface.frames.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonsAddUserForm extends JPanel {
    private JLabel requiredFields;
    private JButton validation, reset;
    private MainWindow parent;
    private User user;
    private JPanel form;
    private CustomerController controller;

    public ButtonsAddUserForm(MainWindow window, JPanel form){
        this.parent = window;
        this.form = form;
        controller = new CustomerController();
        this.setLayout(new FlowLayout());

        requiredFields = new JLabel("*champs obligatoires");
        this.add(requiredFields);

        validation = new JButton("Insertion");
        this.add(validation);

        reset = new JButton("Réinitialisation");
        this.add(reset);


        validation.addActionListener(new ValidationListener());
        reset.addActionListener(new ResetListener());
    }

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if(form instanceof CustomerForm){
                    user = ((CustomerForm)form).createCustomer();
                    controller.addCustomer((Customer)user);
                } else
                    user = ((EmployeeForm)form).createEmployee();
                JOptionPane.showMessageDialog(null, user.description(), "Validation de l'inscription", JOptionPane.INFORMATION_MESSAGE);

            } catch (AddException | ConnectionException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            parent.getWindowContainer().removeAll();
            UserForm user = new UserForm(null);
            parent.getWindowContainer().add(user, BorderLayout.NORTH);

            if (form instanceof CustomerForm){
                CustomerForm customer = new CustomerForm(user, null);
                parent.getWindowContainer().add(customer, BorderLayout.CENTER);
                parent.getWindowContainer().add(new ButtonsAddUserForm(parent, customer), BorderLayout.SOUTH);
            }
            else {
                EmployeeForm employee = new EmployeeForm(user);
                parent.getWindowContainer().add(employee, BorderLayout.CENTER);
                parent.getWindowContainer().add(new ButtonsAddUserForm(parent, employee), BorderLayout.SOUTH);
            }
            parent.getWindowContainer().repaint();
            parent.setVisible(true);
        }
    }

    private class GoBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // TODO faire listener
        }
    }
}
