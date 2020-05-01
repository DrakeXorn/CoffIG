package userInterface.panels;

import controller.CoffeeController;
import model.Coffee;
import model.StockLocation;
import model.exceptions.*;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import userInterface.frames.NewCoffeeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import userInterface.utils.InputCheck;

public class NewCoffeeForm extends JPanel {
    private NewCoffeeFrame parent;
    private JLabel coffeeIDLabel, coffeeNameLabel, countryLabel, intensityLabel, weightLabel, dateLabel, priceLabel,
            packagingLabel, momentLabel, alleyLabel, shelfLabel, numberLabel, quantityLabel, expirationDateLabel;
    private JTextField coffeeID, label, weightNeeded, price, packaging, recommendedMoment;
    private JSpinner alley, shelf, number;
    private static JComboBox<String> countries;
    private JSpinner intensity, quantityBought;
    private JDatePicker discoveryDatePicker, expirationDatePicker;
    private JCheckBox isInGrains, isEnvironmentFriendly;
    private JButton resetButton, confirmButton;
    private CoffeeController controller;

    public NewCoffeeForm(NewCoffeeFrame parent) {
        try {
            this.parent = parent;
            setLayout(new GridLayout(16, 2));
            controller = new CoffeeController();
            UtilDateModel model = new UtilDateModel();
            SpinnerModel alleyConstraints = new SpinnerNumberModel(0, 0, 6, 1);
            SpinnerModel shelfConstraints = new SpinnerNumberModel(0, 0, 6, 1);
            SpinnerModel numberConstraints = new SpinnerNumberModel(0, 0, 6, 1);

            coffeeIDLabel = new JLabel("Numéro du café : ");
            coffeeNameLabel = new JLabel("Nom du café* : ");
            countryLabel = new JLabel("Pays d'origine* : ");
            intensityLabel = new JLabel("Intensité du café* : ");
            weightLabel = new JLabel("Poids requis pour la préparation (g)* : ");
            dateLabel = new JLabel("Année de découverte : ");
            expirationDateLabel = new JLabel("Date d'expiration* : ");
            priceLabel = new JLabel("Prix* : ");
            packagingLabel = new JLabel("Poids initial du paquet (kg)* : ");
            momentLabel = new JLabel("Moment de la journée préféré : ");
            alleyLabel = new JLabel("Numéro de l'allée* : ");
            shelfLabel = new JLabel("Numéro de l'étagère* : ");
            numberLabel = new JLabel("Numéro d'emplacement* : ");
            quantityLabel = new JLabel("Nombre de paquets achetés* : ");

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
            intensity = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
            discoveryDatePicker = new JDatePicker(new UtilDateModel(), "yyyy");
            expirationDatePicker = new JDatePicker();
            isInGrains = new JCheckBox("Est en grains");
            isEnvironmentFriendly = new JCheckBox("Cultivé écologiquement");
            resetButton = new JButton("Tout vider");
            confirmButton = new JButton("Ajouter le café");
            quantityBought = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));

            coffeeID.setEnabled(false);
            coffeeID.setText(String.valueOf(controller.getNbrCoffees() + 1));

            setCountries();
            discoveryDatePicker.setTextEditable(true);
            expirationDatePicker.setTextEditable(true);

            coffeeIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            coffeeNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            countryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            intensityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            quantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            weightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            expirationDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            packagingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            momentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            alleyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            shelfLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            isInGrains.setHorizontalAlignment(SwingConstants.CENTER);
            isEnvironmentFriendly.setHorizontalAlignment(SwingConstants.CENTER);

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
            add(dateLabel);
            add(discoveryDatePicker);
            add(quantityLabel);
            add(quantityBought);
            add(expirationDateLabel);
            add(expirationDatePicker);
            add(weightLabel);
            add(weightNeeded);
            add(priceLabel);
            add(price);
            add(packagingLabel);
            add(packaging);
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur lors de la création de l'identifiant du café", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setCountries() {
        if (countries == null) {
            ArrayList<String> countriesList = new ArrayList<>();
            countriesList.add("");
            for (String country : Locale.getISOCountries()) {
                Locale locale = new Locale("fr", country);
                countriesList.add(locale.getDisplayCountry());
            }
            Collections.sort(countriesList);
            for (String country : countriesList)
                countries.addItem(country);
        }
    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.resetForm();
        }
    }

    private class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           InputCheck.checkInputs(label, weightNeeded, price, packaging, alley, shelf, number, countries,
                    intensity, expirationDatePicker, quantityBought);
           JOptionPane.showMessageDialog(NewCoffeeForm.this, "Vous devez remplir tous les champs obligatoires !", "Erreur", JOptionPane.ERROR_MESSAGE);

           try {
               Coffee coffee = new Coffee(label.getText(),
                       Objects.requireNonNull(countries.getSelectedItem()).toString(),
                       (Integer) intensity.getValue(),
                       Double.parseDouble(weightNeeded.getText()),
                       (discoveryDatePicker.getFormattedTextField().getText().isEmpty() ? null : discoveryDatePicker.getModel().getYear()),
                       isInGrains.isSelected(),
                       isEnvironmentFriendly.isSelected(),
                       Double.parseDouble(price.getText()),
                       Double.parseDouble(packaging.getText()),
                       recommendedMoment.getText().toLowerCase(),
                       new StockLocation(Integer.parseInt(alley.getValue().toString()),
                               Integer.parseInt(shelf.getValue().toString()),
                               Integer.parseInt(number.getValue().toString()),
                               Double.parseDouble(price.getText()),
                               Integer.parseInt(quantityBought.getValue().toString()),
                               new GregorianCalendar(expirationDatePicker.getModel().getYear(),
                                       expirationDatePicker.getModel().getMonth(),
                                       expirationDatePicker.getModel().getDay())));

                if (JOptionPane.showConfirmDialog(NewCoffeeForm.this, coffee, "Confirmer l'ajout ?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
                    controller.addCoffee(coffee);
            } catch (IntegerInputException | DoubleInputException exception) {
                JOptionPane.showMessageDialog(NewCoffeeForm.this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ConnectionException connectionException) {
                JOptionPane.showMessageDialog(NewCoffeeForm.this, connectionException.getMessage(), "Erreur lors de l'ajout dans la base de données", JOptionPane.ERROR_MESSAGE);
            } catch (AddCoffeeException addCoffeeException) {
                JOptionPane.showMessageDialog(NewCoffeeForm.this, addCoffeeException.getMessage(), "Erreur lors de l'ajout dans la base de données", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
