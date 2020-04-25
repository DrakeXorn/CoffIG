package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu coffIG;
    private JMenuItem addCustomer, addEmployee;

    private Container windowContainer;
    public MainWindow(){
        super("Menu");
        this.setBounds(100, 50, 800, 600);
        this.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) { System.exit(0); } } );

        windowContainer = this.getContentPane();
        windowContainer.setLayout(new BorderLayout());

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        coffIG = new JMenu("Ajout");
        menuBar.add(coffIG);

        addCustomer = new JMenuItem("Ajout d'un client");
        coffIG.add(addCustomer);
        addCustomer.addActionListener(new AddCustomerListener());

        addEmployee = new JMenuItem("Ajout d'un employé");
        coffIG.add(addEmployee);
        addEmployee.addActionListener(new AddEmployeeListener());

        setVisible(true);
    }

    public Container getWindowContainer() {
        return windowContainer;
    }

    private class AddCustomerListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            windowContainer.removeAll();
            NewUserForm user = new NewUserForm();
            NewCustomerForm customerForm = new NewCustomerForm(user);
            windowContainer.add(user, BorderLayout.NORTH);
            windowContainer.add(customerForm, BorderLayout.CENTER);
            windowContainer.add(new ButtonsUser(MainWindow.this, customerForm), BorderLayout.SOUTH);
            windowContainer.repaint();
            MainWindow.this.setVisible(true);
        }
    }

    private class AddEmployeeListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            windowContainer.removeAll();
            NewUserForm user = new NewUserForm();
            NewEmployeeForm employeeForm = new NewEmployeeForm(user);
            windowContainer.add(user, BorderLayout.NORTH);
            windowContainer.add(employeeForm, BorderLayout.CENTER);
            windowContainer.add(new ButtonsUser(MainWindow.this, employeeForm), BorderLayout.SOUTH);
            windowContainer.repaint();
            MainWindow.this.setVisible(true);
        }
    }
}
