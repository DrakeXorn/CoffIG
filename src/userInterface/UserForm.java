package userInterface;

import controller.CustomerController;
import model.*;
import model.exceptions.*;
import org.jdatepicker.JDatePicker;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class UserForm extends JPanel {
    private JLabel  passwordLable, lastNameLabel, firstNameLebel, secondNameLebel, maidenNameLebel,
            birthdateLabel, streetNameLabel, numberStreetLabel, emailLabel, phoneLabel, localityLabel;
    private JDatePicker birthdate;
    private JPasswordField password;
    private JTextField lastName, firstName, secondName, maidenName,
            streetName, numberStreet, email, phone;
    private JRadioButton male, female;
    private ButtonGroup buttonGroup;
    private JComboBox<Locality> localitiesBox;
    private static ArrayList<Locality> localities;

    private CustomerController controller;

    public UserForm(Customer customerToModify) {
        controller = new CustomerController();
        this.setLayout(new GridLayout(12, 2, 5, 5));

        passwordLable = new JLabel("Mot de passe* :");
        passwordLable.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(passwordLable);
        password = new JPasswordField(customerToModify != null ? customerToModify.getPassword() : null);
        this.add(password);

        lastNameLabel = new JLabel("Nom de famille* :");
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(lastNameLabel);
        lastName = new JTextField(customerToModify != null ? customerToModify.getLastName() : null);
        this.add(lastName);

        firstNameLebel = new JLabel("Prénom* :");
        firstNameLebel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(firstNameLebel);
        firstName = new JTextField(customerToModify != null ? customerToModify.getFirstName() : null);
        this.add(firstName);

        secondNameLebel = new JLabel("Second prénom :");
        secondNameLebel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(secondNameLebel);
        secondName = new JTextField(customerToModify != null ? customerToModify.getSecondName() : null);
        this.add(secondName);

        maidenNameLebel = new JLabel("Nom de jeune fille :");
        maidenNameLebel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(maidenNameLebel);
        maidenName = new JTextField(customerToModify != null ? customerToModify.getMaidenName() : null);
        this.add(maidenName);

        male = new JRadioButton("Homme", customerToModify != null ? customerToModify.getGender() == 'M' ? true : false : true);
        male.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(male);
        female = new JRadioButton("Femme", customerToModify != null ? customerToModify.getGender() == 'F' ? true : false : false);
        female.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(female);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(male);
        buttonGroup.add(female);

        birthdateLabel = new JLabel("Date de naissance* :");
        birthdateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(birthdateLabel);
        birthdate = new JDatePicker();
        birthdate.setShowYearButtons(true);
        if(customerToModify != null) {
            birthdate.getModel().setYear(customerToModify.getBirthDate().get(Calendar.YEAR));
            birthdate.getModel().setMonth(customerToModify.getBirthDate().get(Calendar.MONTH));
            birthdate.getModel().setDay(customerToModify.getBirthDate().get(Calendar.DAY_OF_MONTH));
        } else {
            birthdate.getModel().setYear(2020);
            birthdate.getModel().setMonth(0);
            birthdate.getModel().setDay(1);
        }
        birthdate.getModel().setSelected(true);
        this.add(birthdate);

        streetNameLabel = new JLabel("Adresse* :");
        streetNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(streetNameLabel);
        streetName = new JTextField(customerToModify != null ? customerToModify.getStreetName().split(", ", 2)[0] : null);
        this.add(streetName);

        numberStreetLabel = new JLabel("Numéro* :");
        numberStreetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(numberStreetLabel);
        numberStreet = new JTextField(customerToModify != null ? customerToModify.getStreetName().split(", ", 2)[1] : null);
        this.add(numberStreet);

        localityLabel = new JLabel("Localité* :");
        localityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(localityLabel);

        try {
            localities = controller.getAllLocalities();
            localitiesBox = new JComboBox<Locality>();
            for (int i = 0; i < localities.size(); i++)
                localitiesBox.addItem(localities.get(i));
            localitiesBox.setMaximumRowCount(5);
            if(customerToModify != null)
                localitiesBox.setSelectedItem(customerToModify.getLocality()); // ne fonctionne pas
            this.add(localitiesBox);

        } catch (AllDataException | ConnectionException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.INFORMATION_MESSAGE);
        }

        emailLabel = new JLabel("Email* :");
        emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(emailLabel);
        email = new JTextField(customerToModify != null ? customerToModify.getEmail() : null);
        this.add(email);

        phoneLabel = new JLabel("Numéro de GSM* :");
        phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(phoneLabel);
        phone = new JTextField(customerToModify != null ? customerToModify.getPhone() : null);
        this.add(phone);
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public String getLastName() {
        return lastName.getText();
    }

    public String getFirstName() {
        return firstName.getText();
    }

    public String getSecondName() {
        return secondName.getText();
    }

    public String getMaidenName() {
        return maidenName.getText();
    }

    public GregorianCalendar getBirthdate() {
        return (GregorianCalendar)birthdate.getModel().getValue();
    }

    public String getStreetName() throws StringInputException {
        if(!numberStreet.getText().matches("^\\d*\\D?$"))
            throw new StringInputException(numberStreet.getText(), "le numéro de l'adresse", "Le numéro se compose d'un ou de plusieurs chiffres et peut parfois être suivi d'une lettre");
        return streetName.getText() + ", " + numberStreet.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getPhone() {
        return phone.getText();
    }

    public Locality getLocality (){
        Locality locality = localities.get(localitiesBox.getSelectedIndex());
        return new Locality(locality.getPostalCode(), locality.getCity());
    }

    public Character getGender() {
        return male.isSelected() ? 'M' : 'F';
    }
}
