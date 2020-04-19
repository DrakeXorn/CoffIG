package view;

import model.*;
import model.exceptions.*;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

public class UserForm extends JPanel {
    private JLabel birthdateLabel, streetNameLabel, emailLabel, phoneLabel, localityPostalCodeLabel, localityCityLabel;
    private JDatePicker birthdate;
    private JTextField streetName, email, phone, localityPostalCode, localityCity;
    private JRadioButton male, female;
    private ButtonGroup buttonGroup;

    public UserForm(){
        this.setLayout(new GridLayout(9, 2, 5, 5));

        birthdateLabel = new JLabel("Date de naissance :");
        birthdateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(birthdateLabel);
        birthdate = new JDateComponentFactory().createJDatePicker();
        birthdate.setTextEditable(true);
        birthdate.setShowYearButtons(true);
        birthdate.getModel().setYear(2020);
        birthdate.getModel().setMonth(0);
        birthdate.getModel().setDay(1);
        birthdate.getModel().setSelected(true);
        this.add((JComponent) birthdate);

        streetNameLabel = new JLabel("Adresse (rue, numéro) :");
        streetNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(streetNameLabel);
        streetName = new JTextField();
        this.add(streetName);

        emailLabel = new JLabel("Email :");
        emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(emailLabel);
        email = new JTextField();
        this.add(email);

        phoneLabel = new JLabel("Numéro de téléphone :");
        phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(phoneLabel);
        phone = new JTextField();
        this.add(phone);

        male = new JRadioButton("Homme", true);
        this.add(male);
        female = new JRadioButton("Femme", false);
        this.add(female);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(male);
        buttonGroup.add(female);

        localityPostalCodeLabel = new JLabel("Code postal :");
        localityPostalCodeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(localityPostalCodeLabel);
        localityPostalCode = new JTextField();
        this.add(localityPostalCode);

        localityCityLabel = new JLabel("Localité :");
        localityCityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(localityCityLabel);
        localityCity = new JTextField();
        this.add(localityCity);
    }

    public GregorianCalendar getBirthdate() {
        return (GregorianCalendar)birthdate.getModel().getValue();
    }

    public String getStreetName() {
        return streetName.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getPhone() {
        return phone.getText();
    }

    public Locality getLocality (){
        return new Locality(Integer.parseInt(localityPostalCode.getText()), localityCity.getText());
    }

    public Character getGender() {
        return male.isSelected() ? 'M' : 'F';
    }

}
