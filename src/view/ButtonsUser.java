package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonsUser extends JPanel {
    private JButton validation, reset;
    private MainWindow parent;
    private Customer customer;
    private CustomerForm customerForm;
    //private EmployeeForm employeeForm;

    public ButtonsUser(MainWindow mainWindow, CustomerForm customerForm/*, EmployeeForm employeeForm*/){
        this.parent = mainWindow;
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
                if (customerForm != null)
                    customer = customerForm.createCustomer();
                //else if (employeeForm != null)
                    //employeeForm = employeeForm.createEmployee();
            }
            else if( even.getSource() == reset){
                parent.getWindowContainer().removeAll();
                UserForm user = new UserForm();
                parent.getWindowContainer().add(user, BorderLayout.NORTH);
                if (customerForm != null){
                    CustomerForm customer = new CustomerForm(user);
                    parent.getWindowContainer().add(customer, BorderLayout.CENTER);
                    parent.getWindowContainer().add(new ButtonsUser(parent, customer/*, null*/), BorderLayout.SOUTH);
                }
                //else if (employeeForm != null) {
                //    EmployeeForm employee = new EmployeeForm(user);
                //    parent.getWindowContainer().add(employee, BorderLayout.CENTER);
                 //   parent.getWindowContainer().add(new ButtonsUserPanel(parent, null, employee), BorderLayout.SOUTH);
                //}
                parent.getWindowContainer().repaint();
                parent.setVisible(true);
            }
        }
    }
}
