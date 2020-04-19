package view;

import model.*;
import model.exceptions.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerForm extends JPanel {

    private JLabel satisfactionDegree;
    private JCheckBox wantsAdvertising, wantsSatisfactionDegree;
    private JRadioButton un, deux, trois, quatre, cinq;
    private ButtonGroup buttonGroup;
    private UserForm userInfos;

    public CustomerForm(UserForm userInfos){
        this.setLayout(new GridLayout(8, 1, 5, 5));
        this.userInfos = userInfos;

        wantsAdvertising = new JCheckBox("Je souhaite recevoir la newsletter");
        this.add(wantsAdvertising);

        wantsSatisfactionDegree = new JCheckBox("Je souhaite partager mon degré de satisfaction");
        this.add(wantsSatisfactionDegree);
        SatisfactionDegreeListener satisfactionDegreeListener = new SatisfactionDegreeListener();
        wantsSatisfactionDegree.addItemListener(satisfactionDegreeListener);

        satisfactionDegree = new JLabel("Degré de satisfaction (entre 1 et 5) :");
        this.add(satisfactionDegree);

        un = new JRadioButton("1", false);
        this.add(un);
        deux = new JRadioButton("2", false);
        this.add(deux);
        trois = new JRadioButton("3", true);
        this.add(trois);
        quatre = new JRadioButton("4", false);
        this.add(quatre);
        cinq = new JRadioButton("5", false);
        this.add(cinq);

        un.setEnabled(false);
        deux.setEnabled(false);
        trois.setEnabled(false);
        quatre.setEnabled(false);
        cinq.setEnabled(false);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(un);
        buttonGroup.add(deux);
        buttonGroup.add(trois);
        buttonGroup.add(quatre);
        buttonGroup.add(cinq);
    }

    private class SatisfactionDegreeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if(event.getStateChange() == ItemEvent.SELECTED){
                un.setEnabled(true);
                deux.setEnabled(true);
                trois.setEnabled(true);
                quatre.setEnabled(true);
                cinq.setEnabled(true);
            }
            else{
                un.setEnabled(false);
                deux.setEnabled(false);
                trois.setEnabled(false);
                quatre.setEnabled(false);
                cinq.setEnabled(false);
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

            customer.setSatisfactionDegree(3);
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
