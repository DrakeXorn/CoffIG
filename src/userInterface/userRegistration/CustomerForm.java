package userInterface.userRegistration;

import controller.UserController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CustomerForm extends JPanel {
    private JCheckBox wantsAdvertising, wantsSatisfactionDegree, wantsLoyaltyCard;
    private JLabel satisfactionDegreeLabel;
    private JSpinner degree;
    private UserForm userInfos;
    private Customer customerToUpdate;

    public CustomerForm(UserForm userInfos, Customer customerToUpdate){
        this.setLayout(new GridLayout(4, 1, 5, 5));
        this.userInfos = userInfos;
        this.customerToUpdate = customerToUpdate;

        this.add(new JLabel(""));

        wantsSatisfactionDegree = new JCheckBox("Je souhaite partager mon degré de satisfaction");
        wantsSatisfactionDegree.setHorizontalAlignment(SwingConstants.CENTER);
        //wantsSatisfactionDegree.setSelected(customerToUpdate != null && customerToUpdate.getSatisfactionDegree() != null);
        this.add(wantsSatisfactionDegree);
        wantsSatisfactionDegree.addItemListener(new WantsSatisfactionDegreeListener());

        satisfactionDegreeLabel = new JLabel("Degré de satisfaction (entre 1 et 5) :");
        satisfactionDegreeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(satisfactionDegreeLabel);

        /*if(customerToUpdate != null && customerToUpdate.getSatisfactionDegree() != null){
            degree = new JSpinner(new SpinnerNumberModel((int) customerToUpdate.getSatisfactionDegree(), 1, 5, 1));

            degree.setEnabled(true);
        } else {
            degree = new JSpinner(new SpinnerNumberModel( 1, 1, 5, 1));
            degree.setEnabled(false);
        }*/
        degree = new JSpinner();
        this.add(degree);

        wantsLoyaltyCard = new JCheckBox("Je souhaite posséder une carte de fidélité");
        wantsLoyaltyCard.setHorizontalAlignment(SwingConstants.CENTER);
        //wantsLoyaltyCard.setSelected(customerToUpdate != null && customerToUpdate.getLoyaltyCard() != null);
        this.add(wantsLoyaltyCard);

        wantsAdvertising = new JCheckBox("Je souhaite recevoir la newsletter");
        wantsAdvertising.setHorizontalAlignment(SwingConstants.CENTER);
        //wantsAdvertising.setSelected(customerToUpdate != null && customerToUpdate.getWantsAdvertising());
        this.add(wantsAdvertising);

        setCustomerDatas(customerToUpdate);
    }

    public void setCustomerDatas(Customer customerToUpdate){
        if(customerToUpdate != null){
            if(customerToUpdate.getSatisfactionDegree() != null){
                wantsSatisfactionDegree.setSelected(true);
                degree.setModel(new SpinnerNumberModel((int) customerToUpdate.getSatisfactionDegree(), 1, 5, 1));
                degree.setEnabled(true);
            } else {
                wantsSatisfactionDegree.setSelected(false);
                degree.setModel(new SpinnerNumberModel( 1, 1, 5, 1));
                degree.setEnabled(false);
            }
            wantsLoyaltyCard.setSelected(customerToUpdate.getLoyaltyCard() != null);
            wantsAdvertising.setSelected(customerToUpdate.getWantsAdvertising());
        } else {
            wantsSatisfactionDegree.setSelected(false);
            degree.setModel(new SpinnerNumberModel( 1, 1, 5, 1));
            degree.setEnabled(false);

            wantsLoyaltyCard.setSelected(false);
            wantsAdvertising.setSelected(false);
        }
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
        UserController controller = new UserController();
        try {
            customer = new Customer(controller.getLastCustomerId() + 1, userInfos.getPassword(), userInfos.getLastName(), userInfos.getFirstName(),
                    userInfos.getSecondName(), userInfos.getMaidenName(), userInfos.getBirthDate(),
                    userInfos.getStreetName(), userInfos.getLocality(), userInfos.getEmail(), userInfos.getPhone(),
                    userInfos.getGender(), wantsAdvertising.isSelected());

            if(wantsSatisfactionDegree.isSelected())
                customer.setSatisfactionDegree((Integer)degree.getValue());

            if(wantsLoyaltyCard.isSelected())
                customer.addLoyaltyCard(new LoyaltyCard((GregorianCalendar)Calendar.getInstance(), customer));

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
        return customer;
    }

    public Customer updateCustomer() {
        Customer customer = null;
        try {
            customer = new Customer(userInfos.getUserId(), userInfos.getPassword(), userInfos.getLastName(), userInfos.getFirstName(),
                    userInfos.getSecondName(), userInfos.getMaidenName(), userInfos.getBirthDate(),
                    userInfos.getStreetName(), userInfos.getLocality(), userInfos.getEmail(), userInfos.getPhone(),
                    userInfos.getGender(), wantsAdvertising.isSelected());

            customer.setSatisfactionDegree(wantsSatisfactionDegree.isSelected() ? (Integer)degree.getValue() : null);

            if(wantsLoyaltyCard.isSelected()) {
                customer.addLoyaltyCard(customerToUpdate.getLoyaltyCard() != null ? customerToUpdate.getLoyaltyCard() :
                        new LoyaltyCard((GregorianCalendar)Calendar.getInstance(), customer));
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
        return customer;
    }
}
