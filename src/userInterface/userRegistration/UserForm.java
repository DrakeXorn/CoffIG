package userInterface.userRegistration;

import com.github.lgooddatepicker.components.DatePicker;
import controller.UserController;
import model.*;
import model.exceptions.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

public class UserForm extends JPanel {
    private JLabel  userIdLabel, passwordLabel, lastNameLabel, firstNameLabel, secondNameLabel, maidenNameLabel,
            birthDateLabel, streetNameLabel, numberStreetLabel, emailLabel, phoneLabel, localityLabel;
    private JPasswordField password;
    private JTextField userId, lastName, firstName, secondName, maidenName,
            streetName, streetNumber, email, phone;
    private JRadioButton maleButton, femaleButton;
    private ButtonGroup buttonGroup;
    private DatePicker birthDatePicker;
    private JComboBox<Locality> localitiesBox;
    private static ArrayList<Locality> localities;

    public UserForm(User userToUpdate) {
        try {
            UserController controller = new UserController();
            this.setLayout(new GridLayout(13, 2, 5, 5));

            userIdLabel = new JLabel("Identifiant :");
            userIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(userIdLabel);
            userId = new JTextField();
            userId.setEnabled(false);
            this.add(userId);

            passwordLabel = new JLabel("Mot de passe* :");
            passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(passwordLabel);
            password = new JPasswordField();
            this.add(password);

            lastNameLabel = new JLabel("Nom de famille* :");
            lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(lastNameLabel);
            lastName = new JTextField();
            this.add(lastName);

            firstNameLabel = new JLabel("Prénom* :");
            firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(firstNameLabel);
            firstName = new JTextField();
            this.add(firstName);

            secondNameLabel = new JLabel("Second prénom :");
            secondNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(secondNameLabel);
            secondName = new JTextField();
            this.add(secondName);

            maidenNameLabel = new JLabel("Nom de jeune fille :");
            maidenNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(maidenNameLabel);

            maidenName = new JTextField();
            maidenName.setEditable(false);
            this.add(maidenName);

            maleButton = new JRadioButton("Homme");
            maleButton.setHorizontalAlignment(SwingConstants.CENTER);
            maleButton.addActionListener(new RadioButtonsListener());
            this.add(maleButton);
            femaleButton = new JRadioButton("Femme");
            femaleButton.setHorizontalAlignment(SwingConstants.CENTER);
            femaleButton.addActionListener(new RadioButtonsListener());
            this.add(femaleButton);

            buttonGroup = new ButtonGroup();
            buttonGroup.add(maleButton);
            buttonGroup.add(femaleButton);

            birthDateLabel = new JLabel("Date de naissance* :");
            birthDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(birthDateLabel);

            birthDatePicker = new DatePicker();
            this.add(birthDatePicker);

            streetNameLabel = new JLabel("Rue* :");
            streetNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(streetNameLabel);
            streetName = new JTextField();
            this.add(streetName);

            numberStreetLabel = new JLabel("Numéro* :");
            numberStreetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(numberStreetLabel);
            streetNumber = new JTextField();
            this.add(streetNumber);

            localityLabel = new JLabel("Localité* :");
            localityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(localityLabel);

            localities = controller.getAllLocalities();
            localitiesBox = new JComboBox<>();
            for (Locality locality : localities)
                localitiesBox.addItem(locality);
            localitiesBox.setMaximumRowCount(5);
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

            setUserDatas(userToUpdate, controller);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setUserDatas(User userToUpdate, UserController controller) throws AllDataException, ConnectionException {
        if(userToUpdate != null){
            userId.setText(userToUpdate.getUserID().toString());
            password.setText(userToUpdate.getPassword());
            lastName.setText(userToUpdate.getLastName());
            firstName.setText(userToUpdate.getFirstName());
            secondName.setText(userToUpdate.getSecondName());
            maidenName.setText(userToUpdate.getMaidenName());
            maleButton.setSelected(userToUpdate.getGender() == 'M');
            femaleButton.setSelected(userToUpdate.getGender() == 'F');
            birthDatePicker.setDate(userToUpdate.getBirthDate().toZonedDateTime().toLocalDate());
            String [] street;
            street = userToUpdate.getStreetName().split(", ", 2);
            streetName.setText(street[0]);
            streetNumber.setText(street[1]);
            localitiesBox.setSelectedItem(userToUpdate.getLocality());
            email.setText(userToUpdate.getEmail());
            phone.setText(userToUpdate.getPhone());
        } else {
            userId.setText(String.valueOf(controller.getLastCustomerId() + 1));
            maleButton.setSelected(true);
            femaleButton.setSelected(false);
            birthDatePicker.setDate(LocalDate.of(2004, Month.JANUARY, 1));
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
        GregorianCalendar birthDate = new GregorianCalendar();
        birthDate.setTime(Date.from(birthDatePicker.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return birthDate;
    }

    public String getStreetName() throws StringInputException {
        if(!streetNumber.getText().matches("^\\d*\\D?$"))
            throw new StringInputException(streetNumber.getText(), "le numéro de l'adresse", "Le numéro se compose d'un ou de plusieurs chiffres et peut parfois être suivi d'une lettre");
        return streetName.getText() + ", " + streetNumber.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getPhone() {
        return phone.getText();
    }

    public Locality getLocality (){
        return localities.get(localitiesBox.getSelectedIndex());
    }

    public Character getGender() {
        return maleButton.isSelected() ? 'M' : 'F';
    }

    private class RadioButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            maidenName.setEditable(e.getSource() == femaleButton);
        }
    }
}
