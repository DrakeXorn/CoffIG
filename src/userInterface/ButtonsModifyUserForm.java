package userInterface;

import controller.CustomerController;
import model.Customer;
import model.User;
import model.exceptions.AddCustomerException;
import model.exceptions.ConnectionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsModifyUserForm extends JPanel{
    private JLabel requiredFields;
    private JButton modify, goBack;
    private MainWindow parent;
    private User user;
    private JPanel form;
    private CustomerController controller;

    public ButtonsModifyUserForm(MainWindow window, JPanel form){
        this.parent = window;
        this.form = form;
        controller = new CustomerController();
        this.setLayout(new FlowLayout());

        requiredFields = new JLabel("*champs obligatoires");
        this.add(requiredFields);

        modify = new JButton("Modification");
        this.add(modify);
        modify.addActionListener(new ModificationListener());

        goBack = new JButton("Retour");
        this.add(goBack);
        goBack.addActionListener(new GoBackListener());
    }


    private class ModificationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if(form instanceof CustomerForm){
                    user = ((CustomerForm)form).createCustomer();
                    controller.modifyCustomer((Customer)user);
                } else {
                    user = ((NewEmployeeForm)form).createEmployee();
                    //controller.modifyEmployee((Employee)user);
                }
                JOptionPane.showMessageDialog(null, user , "Modification de l'inscription", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class GoBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            parent.getWindowContainer().removeAll();
            AllCustomerFrame allCustomerPanel = new AllCustomerFrame(parent, false);
        }
    }
}
