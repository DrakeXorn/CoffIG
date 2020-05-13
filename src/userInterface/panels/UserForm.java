package userInterface.panels;

import controller.CustomerController;
import model.*;
import model.exceptions.*;
import org.jdatepicker.JDatePicker;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class UserForm extends JPanel {
    private JLabel  userIdLabel, passwordLabel, lastNameLabel, firstNameLabel, secondNameLabel, maidenNameLabel,
            birthDateLabel, streetNameLabel, numberStreetLabel, emailLabel, phoneLabel, localityLabel;
    private JDatePicker birthDate;
    private JPasswordField password;
    private JTextField userId, lastName, firstName, secondName, maidenName,
            streetName, numberStreet, email, phone;
    private JRadioButton male, female;
    private ButtonGroup buttonGroup;
    private JComboBox<Locality> localitiesBox;
    private static ArrayList<Locality> localities;

    private CustomerController controller;

    public UserForm(User userToUpdate) {
        try {
            controller = new CustomerController();
            this.setLayout(new GridLayout(13, 2, 5, 5));

            userIdLabel = new JLabel("Identifiant : ");
            userIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(userIdLabel);

            userId = new JTextField(userToUpdate != null ? userToUpdate.getUserID().toString() : String.valueOf(controller.getLastCustomerId() + 1));

            userId.setEnabled(false);
            this.add(userId);

            passwordLabel = new JLabel("Mot de passe* :");
            passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(passwordLabel);
            password = new JPasswordField(userToUpdate != null ? userToUpdate.getPassword() : null);
            this.add(password);

            lastNameLabel = new JLabel("Nom de famille* :");
            lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(lastNameLabel);
            lastName = new JTextField(userToUpdate != null ? userToUpdate.getLastName() : null);
            this.add(lastName);

            firstNameLabel = new JLabel("Prénom* :");
            firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(firstNameLabel);
            firstName = new JTextField(userToUpdate != null ? userToUpdate.getFirstName() : null);
            this.add(firstName);

            secondNameLabel = new JLabel("Second prénom :");
            secondNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(secondNameLabel);
            secondName = new JTextField(userToUpdate != null ? userToUpdate.getSecondName() : null);
            this.add(secondName);

            maidenNameLabel = new JLabel("Nom de jeune fille :");
            maidenNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(maidenNameLabel);
            maidenName = new JTextField(userToUpdate != null ? userToUpdate.getMaidenName() : null);
            this.add(maidenName);

            male = new JRadioButton("Homme", userToUpdate == null || userToUpdate.getGender() == 'M');
            male.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(male);
            female = new JRadioButton("Femme", userToUpdate != null && userToUpdate.getGender() == 'F');
            female.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(female);

            buttonGroup = new ButtonGroup();
            buttonGroup.add(male);
            buttonGroup.add(female);

            birthDateLabel = new JLabel("Date de naissance* :");
            birthDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(birthDateLabel);
            birthDate = new JDatePicker();
            birthDate.setShowYearButtons(true);
            if(userToUpdate != null) {
                birthDate.getModel().setYear(userToUpdate.getBirthDate().get(Calendar.YEAR));
                birthDate.getModel().setMonth(userToUpdate.getBirthDate().get(Calendar.MONTH));
                birthDate.getModel().setDay(userToUpdate.getBirthDate().get(Calendar.DAY_OF_MONTH));
            } else {
                birthDate.getModel().setYear(2020);
                birthDate.getModel().setMonth(0);
                birthDate.getModel().setDay(1);
            }
            birthDate.getModel().setSelected(true);
            this.add(birthDate);

            streetNameLabel = new JLabel("Adresse* :");
            streetNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(streetNameLabel);
            streetName = new JTextField(userToUpdate != null ? userToUpdate.getStreetName().split(", ", 2)[0] : null);
            this.add(streetName);

            numberStreetLabel = new JLabel("Numéro* :");
            numberStreetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(numberStreetLabel);
            numberStreet = new JTextField(userToUpdate != null ? userToUpdate.getStreetName().split(", ", 2)[1] : null);
            this.add(numberStreet);

            localityLabel = new JLabel("Localité* :");
            localityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(localityLabel);

                localities = controller.getAllLocalities();
                localitiesBox = new JComboBox<>();
                for (Locality locality : localities)
                    localitiesBox.addItem(locality);
                localitiesBox.setMaximumRowCount(5);
                if(userToUpdate != null)
                    localitiesBox.setSelectedItem(userToUpdate.getLocality());
                this.add(localitiesBox);

            emailLabel = new JLabel("Email* :");
            emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(emailLabel);
            email = new JTextField(userToUpdate != null ? userToUpdate.getEmail() : null);
            this.add(email);

            phoneLabel = new JLabel("Numéro de GSM* :");
            phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(phoneLabel);
            phone = new JTextField(userToUpdate != null ? userToUpdate.getPhone() : null);
            this.add(phone);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Integer getUserId() {
        return Integer.parseInt(userId.getText());
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

    public GregorianCalendar getBirthDate() {
        return (GregorianCalendar) birthDate.getModel().getValue();
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
