package userInterface.research.advantage;

import controller.AdvantageController;
import controller.CustomerController;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchAdvantagesForm extends JPanel {
    private JLabel customerIdLabel, dateOfDaysLabel, discountLabel;
    private ArrayList<Customer> customers;
    private JTextField dateOfDay;
    private JComboBox<Customer> customersBox;
    private JComboBox<Double> discountsBox;
    private JRadioButton anyAdvantage, accessibleAdvantage, inaccessibleAdvantage;
    private ButtonGroup buttonGroup;

    public SearchAdvantagesForm() {
        try {
            CustomerController customerController = new CustomerController();
            AdvantageController advantageController = new AdvantageController();
            setLayout(new GridLayout(2, 1, 5, 5));

            JPanel fieldsPanel = new JPanel();
            fieldsPanel.setLayout(new GridLayout(3, 2, 5, 5));

            JPanel radioButtonsPanel = new JPanel();
            radioButtonsPanel.setLayout(new GridLayout(1, 3, 5, 5));

            customerIdLabel = new JLabel("Client : ");
            customerIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            customers = customerController.getAllCustomers();
            customersBox = new JComboBox<>();
            for (Customer customer : customers)
                customersBox.addItem(customer);
            customersBox.setMaximumRowCount(5);

            dateOfDaysLabel = new JLabel("Date du jour : ");
            dateOfDaysLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateOfDay = new JTextField(dateFormat.format(GregorianCalendar.getInstance().getTime()));
            dateOfDay.setEditable(false);

            discountLabel = new JLabel("Remise : ");
            discountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            discountsBox = new JComboBox<>();
            for (Double discount : advantageController.getAllAdvantageDiscount())
                discountsBox.addItem(discount);
            discountsBox.setMaximumRowCount(5);

            anyAdvantage = new JRadioButton("Tous les avantages", true);
            anyAdvantage.setHorizontalAlignment(SwingConstants.CENTER);

            accessibleAdvantage = new JRadioButton("Avantages accessibles", false);
            accessibleAdvantage.setHorizontalAlignment(SwingConstants.CENTER);

            inaccessibleAdvantage = new JRadioButton("Avantages inaccessibles", false);
            inaccessibleAdvantage.setHorizontalAlignment(SwingConstants.CENTER);

            buttonGroup = new ButtonGroup();
            buttonGroup.add(anyAdvantage);
            buttonGroup.add(accessibleAdvantage);
            buttonGroup.add(inaccessibleAdvantage);

            fieldsPanel.add(customerIdLabel);
            fieldsPanel.add(customersBox);
            fieldsPanel.add(dateOfDaysLabel);
            fieldsPanel.add(dateOfDay);
            fieldsPanel.add(discountLabel);
            fieldsPanel.add(discountsBox);
            add(fieldsPanel, BorderLayout.NORTH);

            radioButtonsPanel.add(anyAdvantage);
            radioButtonsPanel.add(accessibleAdvantage);
            radioButtonsPanel.add(inaccessibleAdvantage);
            add(radioButtonsPanel, BorderLayout.CENTER);
            
            setVisible(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(),
                    "Erreur lors de la récupération des remises", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public JComboBox<Customer> getCustomersBox() {
        return customersBox;
    }

    public Double getSelectedDiscount() {
        return (Double) discountsBox.getSelectedItem();
    }

    public int getTypeAdvantage() {
        return anyAdvantage.isSelected() ? 1 : accessibleAdvantage.isSelected() ? 2 : 3;
    }
}
