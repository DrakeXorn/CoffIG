package userInterface.panels;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class NewCoffeeForm extends JPanel {
    private JLabel coffeeIDLabel, coffeeNameLabel, countryLabel, intensityLabel, weightLabel, dateLabel, priceLabel,
            packagingLabel, momentLabel, alleyLabel, shelfLabel, numberLabel;
    private JTextField coffeeID, label, weightNeeded, price, packaging, recommendedMoment;
    private JSpinner alley, shelf, number;
    private JComboBox<String> countries;
    private JSpinner intensity;
    private JDatePickerImpl discoveryDatePicker;
    private JCheckBox isInGrains, isEnvironmentFriendly;
    private JButton resetButton, confirmButton;

    public NewCoffeeForm() {
        setLayout(new GridLayout(14, 2));
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        JDatePanelImpl datePanel;
        SpinnerModel alleyConstraints = new SpinnerNumberModel(1, 1, 6, 1);
        SpinnerModel shelfConstraints = new SpinnerNumberModel(1, 1, 6, 1);
        SpinnerModel numberConstraints = new SpinnerNumberModel(1, 1, 6, 1);

        properties.put("text.today", "aujourd'hui");
        properties.put("text.month", "mois");
        properties.put("text.year", "année");

        datePanel = new JDatePanelImpl(model, properties);
        coffeeIDLabel = new JLabel("Numéro du café : ");
        coffeeNameLabel = new JLabel("Nom du café : ");
        countryLabel = new JLabel("Pays d'origine : ");
        intensityLabel = new JLabel("Intensité du café : ");
        weightLabel = new JLabel("Poids requis pour la préparation : ");
        dateLabel = new JLabel("Date de découverte : "); // TODO Année de découverte, plutôt ? Pas facile de trouver les dates précises. => Si le cas, peut-être changer le type de la date de découverte de la BD en int.
        priceLabel = new JLabel("Prix : ");
        packagingLabel = new JLabel("Quantité initiale du paquet : ");
        momentLabel = new JLabel("Moment de la journée préféré : ");
        alleyLabel = new JLabel("Numéro de l'allée : ");
        shelfLabel = new JLabel("Numéro de l'étagère : ");
        numberLabel = new JLabel("Numéro d'emplacement : ");

        coffeeID = new JTextField();
        label = new JTextField();
        weightNeeded = new JTextField();
        price = new JTextField();
        packaging = new JTextField();
        recommendedMoment = new JTextField();
        alley = new JSpinner(alleyConstraints);
        shelf = new JSpinner(shelfConstraints);
        number = new JSpinner(numberConstraints);
        countries = new JComboBox<>();
        intensity = new JSpinner(new SpinnerNumberModel(3, 1, 5, 1));
        discoveryDatePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        isInGrains = new JCheckBox("Est en grains");
        isEnvironmentFriendly = new JCheckBox("Cultivé écologiquement");
        resetButton = new JButton("Tout vider");
        confirmButton = new JButton("Ajouter le café");

        coffeeID.setEnabled(false);
        // TODO récupérer automatiquement le numéro du café via une requête SQL

        ArrayList<String> countriesList = new ArrayList<>();
        for (String country : Locale.getISOCountries()) {
            Locale locale = new Locale("fr", country);
            countriesList.add(locale.getDisplayCountry());
        }
        Collections.sort(countriesList);
        for (String country : countriesList)
            countries.addItem(country);

        coffeeIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        coffeeNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        countryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        intensityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        weightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        packagingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        momentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        alleyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        shelfLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        resetButton.addActionListener(new ResetListener());
        confirmButton.addActionListener(new ConfirmListener());

        add(coffeeIDLabel);
        add(coffeeID);
        add(coffeeNameLabel);
        add(label);
        add(countryLabel);
        add(countries);
        add(intensityLabel);
        add(intensity);
        add(weightLabel);
        add(weightNeeded);
        add(priceLabel);
        add(price);
        add(packagingLabel);
        add(packaging);
        add(dateLabel);
        add(discoveryDatePicker);
        add(momentLabel);
        add(recommendedMoment);
        add(alleyLabel);
        add(alley);
        add(shelfLabel);
        add(shelf);
        add(numberLabel);
        add(number);
        add(isInGrains);
        add(isEnvironmentFriendly);
        add(resetButton);
        add(confirmButton);

        this.setVisible(true);
    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("");
            weightNeeded.setText("");
            price.setText("");
            packaging.setText("");
            recommendedMoment.setText("");
            alley.setValue(1);
            shelf.setValue(1);
            number.setValue(1);
            countries.setSelectedIndex(0);
            intensity.setValue(3);
            discoveryDatePicker.getJFormattedTextField().setText("");
            isInGrains.setSelected(false);
            isEnvironmentFriendly.setSelected(false);
        }
    }

    private class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
