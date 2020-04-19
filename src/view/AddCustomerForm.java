package view;

import model.*;
import model.exceptions.*;
import javax.swing.*;
import java.awt.*;

public class AddCustomerForm extends JPanel {

    private JLabel satisfactionDegree;
    private JCheckBox wantsAdvertising;
    private JRadioButton un, deux, trois, quatre, cinq;
    private ButtonGroup buttonGroup;

    public AddCustomerForm(){
        this.setLayout(new GridLayout(7, 1, 5, 5));

        wantsAdvertising = new JCheckBox("Je souhaite recevoir la newsletter");
        this.add(wantsAdvertising);

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

        buttonGroup = new ButtonGroup();
        buttonGroup.add(un);
        buttonGroup.add(deux);
        buttonGroup.add(trois);
        buttonGroup.add(quatre);
        buttonGroup.add(cinq);
    }

    public Customer createCustomer(User user) {
        Customer customer = null;
        try {
            customer = new Customer(user.getPassword(), user.getLastName(), user.getFirstName(),
                    user.getSecondName(), user.getMaidenName(), user.getBirthDate(),
                    user.getStreetName(), user.getEmail(), user.getPhone(), user.getGender(),
                    user.getLocality(), wantsAdvertising.isSelected());

            customer.setSatisfactionDegree(3);
            JOptionPane.showMessageDialog(null, customer + "\nVotre identifiant est " + user.getUserID(),
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
