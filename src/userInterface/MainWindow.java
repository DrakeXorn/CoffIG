package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu addNew, modify, displayAll, search;
    private JMenuItem addCustomer, addEmployee, modifyCustomer, allCustomers;
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

        addNew = new JMenu("Ajouter");
        menuBar.add(addNew);

        addCustomer = new JMenuItem("Ajouter un client");
        addNew.add(addCustomer);
        addCustomer.addActionListener(new AddCustomerListener());

        addEmployee = new JMenuItem("Ajouter un employé");
        addNew.add(addEmployee);
        addEmployee.addActionListener(new AddEmployeeListener());

        modify = new JMenu("Modifier");
        menuBar.add(modify);

        modifyCustomer = new JMenuItem("Modifier un client");
        modify.add(modifyCustomer);
        modifyCustomer.addActionListener(new ModifyCustomerListener());

        displayAll = new JMenu("Afficher");
        menuBar.add(displayAll);

        allCustomers = new JMenuItem("Afficher tous les clients");
        displayAll.add(allCustomers);
        allCustomers.addActionListener(new AllCustomerListener());

        search = new JMenu("Rechercher");
        menuBar.add(search);

        setVisible(true);
    }

    public Container getWindowContainer() {
        return windowContainer;
    }

    private class AddCustomerListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            windowContainer.removeAll();
            UserForm user = new UserForm(null);
            CustomerForm customerForm = new CustomerForm(user, null);
            windowContainer.add(user, BorderLayout.NORTH);
            windowContainer.add(customerForm, BorderLayout.CENTER);
            windowContainer.add(new ButtonsAddUserForm(MainWindow.this, customerForm), BorderLayout.SOUTH);
            windowContainer.repaint();
            MainWindow.this.setVisible(true);
        }
    }

    private class AddEmployeeListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            windowContainer.removeAll();
            UserForm user = new UserForm(null);
            NewEmployeeForm employeeForm = new NewEmployeeForm(user);
            windowContainer.add(user, BorderLayout.NORTH);
            windowContainer.add(employeeForm, BorderLayout.CENTER);
            windowContainer.add(new ButtonsAddUserForm(MainWindow.this, employeeForm), BorderLayout.SOUTH);
            windowContainer.repaint();
            MainWindow.this.setVisible(true);
        }
    }

    private class ModifyCustomerListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            AllCustomerFrame allCustomerPanel = new AllCustomerFrame(MainWindow.this, false);
        }
    }

    private class AllCustomerListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            AllCustomerFrame allCustomerPanel = new AllCustomerFrame(MainWindow.this, true);
        }
    }
}
