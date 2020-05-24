package userInterface.userRegistration;

import controller.CustomerController;
import model.*;
import userInterface.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsUpdateUserForm extends JPanel{
    private JLabel requiredFields;
    private JButton modify, goBack;
    private MainWindow parent;
    private Customer user;
    private CustomerForm form;

    public ButtonsUpdateUserForm(MainWindow window, CustomerForm form){
        this.parent = window;
        this.form = form;
        CustomerController controller = new CustomerController();
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
                user = form.updateCustomer();

                if(user != null && JOptionPane.showConfirmDialog(null, "Êtes-vous sûr(e) de vouloir modifier " + user + " ?", "Modification de l'inscription", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    CustomerController controller = new CustomerController();

                    controller.updateCustomer(user);
                    JOptionPane.showMessageDialog(null, user + " a été modifié" , "Modification de l'inscription", JOptionPane.INFORMATION_MESSAGE);
                    parent.goBackHome();
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class GoBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            parent.goBackHome();
            new UpdateCustomersFrame(parent);
        }
    }
}
