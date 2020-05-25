package userInterface.userRegistration;

import controller.CustomerController;
import controller.EmployeeController;
import model.*;
import userInterface.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonsAddUserForm extends JPanel {
    private JLabel requiredFields;
    private JButton validation, reset;
    private MainWindow parent;
    private User user;
    private JPanel form;

    public ButtonsAddUserForm(MainWindow window, JPanel form){
        this.parent = window;
        parent.resetSize();
        this.form = form;
        this.setLayout(new FlowLayout());

        requiredFields = new JLabel("*champs obligatoires");
        this.add(requiredFields);

        validation = new JButton("Insertion");
        this.add(validation);

        reset = new JButton("Réinitialisation");
        this.add(reset);

        validation.addActionListener(new ValidationListener());
        reset.addActionListener(new ResetListener());

        setVisible(true);
    }

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if(form instanceof CustomerForm){
                    CustomerController customerController = new CustomerController();
                    user = ((CustomerForm)form).createCustomer();

                    if (user != null && JOptionPane.showConfirmDialog(null, user.description() + ".\nAjouter le client ?", "Validation de l'inscription", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        customerController.addCustomer((Customer)user);
                        JOptionPane.showMessageDialog(null, user + " a bien été inscrit", "Validation de l'inscription", JOptionPane.INFORMATION_MESSAGE);
                        parent.goBackHome();
                    }
                } else {
                    EmployeeController employeeController = new EmployeeController();
                    user = ((EmployeeForm)form).createEmployee();
                    if (user != null && JOptionPane.showConfirmDialog(null, user.description() + ".\nAjouter l'employé ?", "Validation de l'inscription", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        employeeController.addEmployee((Employee)user);
                        JOptionPane.showMessageDialog(null, user + " a bien été inscrit", "Validation de l'inscription", JOptionPane.INFORMATION_MESSAGE);
                        parent.goBackHome();
                    }
                }
            } catch (Exception exception) {
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

            if (form instanceof CustomerForm) {
                CustomerForm customer = new CustomerForm(user, null);
                parent.getWindowContainer().add(customer, BorderLayout.CENTER);
                parent.getWindowContainer().add(new ButtonsAddUserForm(parent, customer), BorderLayout.SOUTH);
            } else {
                EmployeeForm employee = new EmployeeForm(user);
                parent.getWindowContainer().add(employee, BorderLayout.CENTER);
                parent.getWindowContainer().add(new ButtonsAddUserForm(parent, employee), BorderLayout.SOUTH);
            }
            parent.getWindowContainer().repaint();
            parent.setVisible(true);
        }
    }
}
