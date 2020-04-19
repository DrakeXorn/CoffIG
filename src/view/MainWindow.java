package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu coffIG;
    private JMenuItem customer;

    private Container windowContainer;
    public MainWindow(){
        super("Menu");
        this.setBounds(100, 50, 800, 500);
        this.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) { System.exit(0); } } );

        windowContainer = this.getContentPane();
        windowContainer.setLayout(new BorderLayout());

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        coffIG = new JMenu("Ajout");
        menuBar.add(coffIG);

        customer = new JMenuItem("Ajout d'un client");
        coffIG.add(customer);
        AddCustomerListener addCustomerListener = new AddCustomerListener();
        customer.addActionListener(addCustomerListener);

        setVisible(true);
    }

    public Container getWindowContainer() {
        return windowContainer;
    }

    private class AddCustomerListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            windowContainer.removeAll();
            UserForm user = new UserForm();
            CustomerForm customer = new CustomerForm(user);
            windowContainer.add(user, BorderLayout.NORTH);
            windowContainer.add(customer, BorderLayout.CENTER);
            windowContainer.add(new ButtonsUser(MainWindow.this, customer), BorderLayout.SOUTH);
            windowContainer.repaint();
            MainWindow.this.setVisible(true);
        }
    }
}
