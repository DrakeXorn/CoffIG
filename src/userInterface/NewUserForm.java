package userInterface;

import model.*;
import model.exceptions.*;
import org.jdatepicker.JDatePicker;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class NewUserForm extends JPanel {
    private JLabel  passwordLable, lastNameLabel, firstNameLebel, secondNameLebel, maidenNameLebel,
            birthdateLabel, streetNameLabel, numberStreetLabel, emailLabel, phoneLabel, localityLabel;
    private JDatePicker birthdate;
    private JPasswordField password;
    private JTextField lastName, firstName, secondName, maidenName,
            streetName, numberStreet, email, phone;
    private JRadioButton male, female;
    private ButtonGroup buttonGroup;
    private JComboBox localitiesBox;
    private static String [] localitiesCity = {"5000 Namur", "5020 Malonne", "5100 Naninne", "5100 Wépion", "5100 Jambes", "5300 Vezin"};

    public NewUserForm(){
        this.setLayout(new GridLayout(12, 2, 5, 5));

        passwordLable = new JLabel("Mot de passe* :");
        passwordLable.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(passwordLable);
        password = new JPasswordField();
        this.add(password);

        lastNameLabel = new JLabel("Nom de famille* :");
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(lastNameLabel);
        lastName = new JTextField();
        this.add(lastName);

        firstNameLebel = new JLabel("Prénom* :");
        firstNameLebel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(firstNameLebel);
        firstName = new JTextField();
        this.add(firstName);

        secondNameLebel = new JLabel("Second prénom :");
        secondNameLebel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(secondNameLebel);
        secondName = new JTextField("");
        this.add(secondName);

        maidenNameLebel = new JLabel("Nom de jeune fille :");
        maidenNameLebel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(maidenNameLebel);
        maidenName = new JTextField("");
        this.add(maidenName);

        male = new JRadioButton("Homme", true);
        male.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(male);
        female = new JRadioButton("Femme", false);
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
        birthdate.getModel().setYear(2020);
        birthdate.getModel().setMonth(0);
        birthdate.getModel().setDay(1);
        birthdate.getModel().setSelected(true);
        this.add(birthdate);

        streetNameLabel = new JLabel("Adresse* :");
        streetNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(streetNameLabel);
        streetName = new JTextField();
        this.add(streetName);

        numberStreetLabel = new JLabel("Numéro* :");
        numberStreetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(numberStreetLabel);
        numberStreet = new JTextField();
        this.add(numberStreet);

        localityLabel = new JLabel("Localité* :");
        localityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(localityLabel);

        localitiesBox = new JComboBox(localitiesCity);
        localitiesBox.setMaximumRowCount(3);
        this.add(localitiesBox);

        emailLabel = new JLabel("Email* :");
        emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(emailLabel);
        email = new JTextField();
        this.add(email);

        phoneLabel = new JLabel("Numéro de GSM* :");
        phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(phoneLabel);
        phone = new JTextField();
        this.add(phone);
    }

    public String getPasseword() {
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
        if(!numberStreet.getText().matches("^\\d*$"))
            throw new StringInputException(numberStreet.getText(), "l");
        return streetName.getText() + ", " + numberStreet.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getPhone() {
        return phone.getText();
    }

    public Locality getLocality (){
        String locality = localitiesCity[localitiesBox.getSelectedIndex()];
        return new Locality(Integer.parseInt(locality.substring(0, 4)), locality.substring(5));
    }

    public Character getGender() {
        return male.isSelected() ? 'M' : 'F';
    }
}
