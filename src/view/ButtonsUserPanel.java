package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonsUserPanel extends JPanel {
    private JButton validation, reset;
    private MainWindow parent;
    private User user;
    private Customer customer;
    private AddUserForm userForm;
    private AddCustomerForm customerForm;
    //private AddEmployeeForm employeeForm;

    public ButtonsUserPanel(MainWindow mainWindow, AddUserForm userForm,
                            AddCustomerForm customerForm/*, AddEmployeeForm employeeForm*/){
        this.parent = mainWindow;
        this.userForm = userForm;
        this.customerForm = customerForm;
        //this. employeeForm = employeeForm;
        this.setLayout(new FlowLayout());

        validation = new JButton("Validation");
        this.add(validation);
        reset = new JButton("Réinitialisation");
        this.add(reset);

        ButtonListener buttonListener = new ButtonListener();
        validation.addActionListener(buttonListener);
        reset.addActionListener(buttonListener);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent even) {
            if (even.getSource() == validation){
                user = userForm.createUser();
                if (customerForm != null)
                    customer = customerForm.createCustomer(user);
                //else if (employeeForm != null)
                    //employeeForm = employeeForm.createCustomer(user);
            }
            else if( even.getSource() == reset){
                parent.getWindowContainer().removeAll();
                AddUserForm user = new AddUserForm();
                parent.getWindowContainer().add(user, BorderLayout.NORTH);
                if (customerForm != null){
                    AddCustomerForm customer = new AddCustomerForm();
                    parent.getWindowContainer().add(customer, BorderLayout.CENTER);
                    parent.getWindowContainer().add(new ButtonsUserPanel(parent, user, customer/*, null*/), BorderLayout.SOUTH);
                }
                //else if (employeeForm != null) {
                //    AddEmployeeForm employee = new AddEmployeeForm();
                //    parent.getWindowContainer().add(employee, BorderLayout.CENTER);
                 //   parent.getWindowContainer().add(new ButtonsUserPanel(parent, user, null, employee), BorderLayout.SOUTH);
                //}
                parent.getWindowContainer().repaint();
                parent.setVisible(true);
            }
        }
    }
}
