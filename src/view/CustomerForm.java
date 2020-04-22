package view;

import model.*;
import model.exceptions.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerForm extends JPanel {
    private JCheckBox wantsAdvertising, wantsSatisfactionDegree;
    private JLabel satisfactionDegreeLabel;
    private JSpinner degree;
    private UserForm userInfos;

    public CustomerForm(UserForm userInfos){
        this.setLayout(new GridLayout(8, 1, 5, 5));
        this.userInfos = userInfos;

        wantsAdvertising = new JCheckBox("Je souhaite recevoir la newsletter");
        this.add(wantsAdvertising);

        wantsSatisfactionDegree = new JCheckBox("Je souhaite partager mon degré de satisfaction");
        this.add(wantsSatisfactionDegree);
        wantsSatisfactionDegree.addItemListener(new WantsSatisfactionDegreeListener());

        satisfactionDegreeLabel = new JLabel("Degré de satisfaction (entre 1 et 5) :");
        this.add(satisfactionDegreeLabel);
        degree = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        degree.setEnabled(false);
        this.add(degree);
    }

    private class WantsSatisfactionDegreeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if(event.getStateChange() == ItemEvent.SELECTED){
                degree.setEnabled(true);
            }
            else{
                degree.setEnabled(false);
                degree.setValue(null);
            }
        }
    }

    public Customer createCustomer() {
        Customer customer = null;
        try {
            customer = new Customer("password", "last name", "first name",
                    "second name", "maiden name", userInfos.getBirthdate(),
                    userInfos.getStreetName(), userInfos.getEmail(), userInfos.getPhone(), userInfos.getGender(),
                    userInfos.getLocality(), wantsAdvertising.isSelected());

            if(wantsSatisfactionDegree.isSelected())
                customer.setSatisfactionDegree((Integer)degree.getValue());

            JOptionPane.showMessageDialog(null, customer + "\nVotre identifiant est " + customer.getUserID(),
                    "Validation de l'inscription", JOptionPane.INFORMATION_MESSAGE);
        } catch (GenderException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.INFORMATION_MESSAGE);
        } catch (SatisfactionDegreeException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.INFORMATION_MESSAGE);
        }
        return customer;
    }

}
