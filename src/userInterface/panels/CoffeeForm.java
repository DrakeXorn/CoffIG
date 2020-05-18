package userInterface.panels;

import controller.CoffeeController;
import controller.StockLocationController;
import model.Coffee;
import model.StockLocation;
import model.exceptions.*;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import userInterface.frames.FeaturesManagementFrame;
import userInterface.frames.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class CoffeeForm extends JPanel {
    private MainWindow parent;
    private JLabel coffeeIDLabel, coffeeNameLabel, countryLabel, intensityLabel, weightLabel, discoveryDateLabel, priceLabel,
            packagingLabel, momentLabel, alleyLabel, shelfLabel, numberLabel, quantityLabel, expirationDateLabel, showFeaturesLabel;
    private JTextField coffeeID, coffeeName, weightNeeded, price, packaging, recommendedMoment;
    private JSpinner alley, shelf, number;
    private JComboBox<String> countries;
    private JSpinner intensity, quantityBought;
    private JDatePicker discoveryDatePicker, expirationDatePicker;
    private JCheckBox isInGrains, isEnvironmentFriendly;
    private JButton showFeaturesButton;
    private CoffeeController controller;
    private ArrayList<String> features;
    private Coffee coffee;

    public CoffeeForm(MainWindow parent, Coffee coffeeToUpdate) {
        try {
            this.parent = parent;
            setLayout(new GridLayout(16, 2));
            controller = new CoffeeController();
            ArrayList<String> countriesList = new ArrayList<>();

            coffeeIDLabel = new JLabel("Numéro du café : ");
            coffeeIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(coffeeIDLabel);

            coffeeID = new JTextField();
            coffeeID.setEnabled(false);
            add(coffeeID);

            coffeeNameLabel = new JLabel("Nom du café* : ");
            coffeeNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(coffeeNameLabel);

            coffeeName = new JTextField();
            add(coffeeName);

            countryLabel = new JLabel("Pays d'origine* : ");
            countryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(countryLabel);

            countriesList.add("");
            for (String country : Locale.getISOCountries()) {
                Locale locale = new Locale("fr", country);
                countriesList.add(locale.getDisplayCountry());
            }
            Collections.sort(countriesList);

            countries = new JComboBox<>();
            for (String country : countriesList)
                countries.addItem(country);
            add(countries);

            intensityLabel = new JLabel("Intensité du café* : ");
            intensityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(intensityLabel);

            intensity = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
            add(intensity);

            discoveryDateLabel = new JLabel("Année de découverte : ");
            discoveryDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(discoveryDateLabel);

            discoveryDatePicker = new JDatePicker(new UtilDateModel(), "yyyy");
            discoveryDatePicker.setTextEditable(true);
            add(discoveryDatePicker);

            quantityLabel = new JLabel("Nombre de paquets achetés* : ");
            quantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(quantityLabel);

            quantityBought = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
            add(quantityBought);

            expirationDateLabel = new JLabel("Date d'expiration* : ");
            expirationDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(expirationDateLabel);

            expirationDatePicker = new JDatePicker();
            expirationDatePicker.setTextEditable(true);
            add(expirationDatePicker);

            weightLabel = new JLabel("Poids requis pour la préparation (g)* : ");
            weightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(weightLabel);

            weightNeeded = new JTextField();
            add(weightNeeded);

            priceLabel = new JLabel("Prix* : ");
            priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(priceLabel);

            price = new JTextField();
            add(price);

            packagingLabel = new JLabel("Poids initial du paquet (kg)* : ");
            packagingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(packagingLabel);

            packaging = new JTextField();
            add(packaging);

            momentLabel = new JLabel("Moment de la journée préféré : ");
            momentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(momentLabel);

            recommendedMoment = new JTextField();
            add(recommendedMoment);

            alleyLabel = new JLabel("Numéro de l'allée* : ");
            alleyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(alleyLabel);

            alley = new JSpinner(new SpinnerNumberModel(0, 0, 6, 1));
            add(alley);

            shelfLabel = new JLabel("Numéro de l'étagère* : ");
            shelfLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(shelfLabel);

            shelf = new JSpinner(new SpinnerNumberModel(0, 0, 6, 1));
            add(shelf);

            numberLabel = new JLabel("Numéro d'emplacement* : ");
            numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(numberLabel);

            number = new JSpinner(new SpinnerNumberModel(0, 0, 6, 1));
            add(number);

            isInGrains = new JCheckBox("Est en grains");
            isInGrains.setHorizontalAlignment(SwingConstants.CENTER);
            add(isInGrains);

            isEnvironmentFriendly = new JCheckBox("Cultivé écologiquement");
            isEnvironmentFriendly.setHorizontalAlignment(SwingConstants.CENTER);
            add(isEnvironmentFriendly);

            showFeaturesLabel = new JLabel("Caractéristiques : ");
            showFeaturesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(showFeaturesLabel);

            showFeaturesButton = new JButton("Gérer les caractéristiques");
            showFeaturesButton.addActionListener(new ShowFeaturesListener());
            add(showFeaturesButton);

            setCoffeeData(coffeeToUpdate);

            this.setVisible(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur lors de la création de l'identifiant du café", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setCoffeeData(Coffee coffee) throws ConnectionException, AddDataException {
        if (coffee != null) {
            this.coffee = coffee;
            coffeeID.setText(String.valueOf(coffee.getCoffeeID()));
            coffeeName.setText(coffee.getLabel());
            countries.setSelectedItem(coffee.getOriginCountry());
            intensity.setValue(coffee.getIntensity());
            if (coffee.getDiscoveryYear() != null) {
                discoveryDatePicker.getModel().setYear(coffee.getDiscoveryYear());
                discoveryDatePicker.getModel().setSelected(true);
            }
            expirationDatePicker.getModel().setYear(coffee.getStockLocation().getExpirationDate().get(GregorianCalendar.YEAR));
            expirationDatePicker.getModel().setMonth(coffee.getStockLocation().getExpirationDate().get(GregorianCalendar.MONTH));
            expirationDatePicker.getModel().setDay(coffee.getStockLocation().getExpirationDate().get(GregorianCalendar.DAY_OF_MONTH));
            expirationDatePicker.getModel().setSelected(true);
            quantityBought.getModel().setValue(coffee.getStockLocation().getQuantity());
            weightNeeded.setText(String.valueOf(coffee.getWeightNeededForPreparation()));
            price.setText(String.valueOf(coffee.getPrice()));
            packaging.setText(coffee.getPackaging().toString());
            recommendedMoment.setText(coffee.getRecommendedConsumingMoment());
            alley.setValue(coffee.getStockLocation().getAlley());
            alley.setEnabled(false);
            shelf.setValue(coffee.getStockLocation().getShelf());
            shelf.setEnabled(false);
            number.setValue(coffee.getStockLocation().getNumber());
            number.setEnabled(false);
            isInGrains.setSelected(coffee.isInGrains());
            isEnvironmentFriendly.setSelected(coffee.isEnvironmentFriendly());
            features = coffee.getFeatures();
        } else {
            coffeeID.setText(String.valueOf(controller.getLastCoffeeID() + 1));
        }
    }

    public Coffee createCoffee() throws ConnectionException, DoubleInputException, IntegerInputException, AllDataException, DateException {
        StockLocationController stockLocationController = new StockLocationController();
        ArrayList<StockLocation> stockLocations = stockLocationController.getAllStockLocations();
        GregorianCalendar expirationDate = new GregorianCalendar(expirationDatePicker.getModel().getYear(), expirationDatePicker.getModel().getMonth() + 1, expirationDatePicker.getModel().getDay());
        GregorianCalendar discoveryDate = new GregorianCalendar(discoveryDatePicker.getModel().getYear(), discoveryDatePicker.getModel().getMonth() + 1, discoveryDatePicker.getModel().getDay());
        StockLocation coffeeLocation;

        coffeeLocation = new StockLocation(Integer.parseInt(alley.getValue().toString()),
                Integer.parseInt(shelf.getValue().toString()),
                Integer.parseInt(number.getValue().toString()),
                Double.parseDouble(price.getText()),
                Integer.parseInt(quantityBought.getValue().toString()),
                expirationDate);

        return (!stockLocations.contains(coffeeLocation) || coffeeLocation.equals(coffee.getStockLocation())) ? new Coffee(Integer.parseInt(coffeeID.getText()),
                coffeeName.getText(),
                Objects.requireNonNull(countries.getSelectedItem()).toString(),
                (Integer) intensity.getValue(),
                Double.parseDouble(weightNeeded.getText()),
                discoveryDate,
                isInGrains.isSelected(),
                isEnvironmentFriendly.isSelected(),
                Double.parseDouble(price.getText()),
                Double.parseDouble(packaging.getText()),
                recommendedMoment.getText().toLowerCase(),
                coffeeLocation) : null;
    }

    public CoffeeController getController() {
        return controller;
    }

    public JTextField getCoffeeID() {
        return coffeeID;
    }

    public JTextField getWeightNeeded() {
        return weightNeeded;
    }

    public JTextField getPrice() {
        return price;
    }

    public JTextField getPackaging() {
        return packaging;
    }

    public JSpinner getAlley() {
        return alley;
    }

    public JSpinner getShelf() {
        return shelf;
    }

    public JSpinner getNumber() {
        return number;
    }

    public JComboBox<String> getCountries() {
        return countries;
    }

    public JSpinner getIntensity() {
        return intensity;
    }

    public JSpinner getQuantityBought() {
        return quantityBought;
    }

    public JDatePicker getExpirationDatePicker() {
        return expirationDatePicker;
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<String> features) {
        this.features = features;
    }

    private class ShowFeaturesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FeaturesManagementFrame frame = new FeaturesManagementFrame(CoffeeForm.this);
        }
    }
}
