package userInterface;

import model.*;
import model.exceptions.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class NewCustomerForm extends JPanel {
    private JCheckBox wantsAdvertising, wantsSatisfactionDegree, wantsLoyaltyCard;
    private JLabel satisfactionDegreeLabel;
    private JSpinner degree;
    private NewUserForm userInfos;

    public NewCustomerForm(NewUserForm userInfos){
        this.setLayout(new GridLayout(4, 1, 5, 5));
        this.userInfos = userInfos;

        this.add(new JLabel(""));

        wantsSatisfactionDegree = new JCheckBox("Je souhaite partager mon degré de satisfaction");
        wantsSatisfactionDegree.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(wantsSatisfactionDegree);
        wantsSatisfactionDegree.addItemListener(new WantsSatisfactionDegreeListener());

        satisfactionDegreeLabel = new JLabel("Degré de satisfaction (entre 1 et 5) :");
        satisfactionDegreeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(satisfactionDegreeLabel);
        degree = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        degree.setEnabled(false);
        this.add(degree);

        wantsLoyaltyCard = new JCheckBox("Je souhaite posséder une carte de fidélité");
        wantsLoyaltyCard.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(wantsLoyaltyCard);

        wantsAdvertising = new JCheckBox("Je souhaite recevoir la newsletter");
        wantsAdvertising.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(wantsAdvertising);
    }

    private class WantsSatisfactionDegreeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if(event.getStateChange() == ItemEvent.SELECTED)
                degree.setEnabled(true);
            else{
                degree.setEnabled(false);
                degree.setValue(1);
            }
        }
    }

    public Customer createCustomer() {
        Customer customer = null;
        try {
            customer = new Customer(userInfos.getPasseword(), userInfos.getLastName(), userInfos.getFirstName(),
                    userInfos.getSecondName(), userInfos.getMaidenName(), userInfos.getBirthdate(),
                    userInfos.getStreetName(), userInfos.getLocality(), userInfos.getEmail(), userInfos.getPhone(),
                    userInfos.getGender(), wantsAdvertising.isSelected());

            if(wantsSatisfactionDegree.isSelected())
                customer.setSatisfactionDegree((Integer)degree.getValue());

            if(wantsLoyaltyCard.isSelected())
                customer.addLoyaltyCard(new LoyaltyCard((GregorianCalendar)Calendar.getInstance(), 100, customer));

            JOptionPane.showMessageDialog(null, customer,
                    "Validation de l'inscription", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.INFORMATION_MESSAGE);
        }
        return customer;
    }
}
