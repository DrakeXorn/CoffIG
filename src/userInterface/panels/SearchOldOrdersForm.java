package userInterface.panels;

import controller.CustomerController;
import model.Customer;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchOldOrdersForm extends JPanel {
    private JLabel customerIdLabel, startDateLabel, endDateLabel;
    private ArrayList<Customer> customers;
    private JComboBox <Customer> customersBox;
    private JDatePicker startDate, endDate;
    private JCheckBox takeAway, onSite;
    private CustomerController controller;

    public SearchOldOrdersForm() {
        try {
            controller = new CustomerController();
            this.setLayout(new GridLayout(4, 2, 5, 5));

            customerIdLabel = new JLabel("Client :");
            customerIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(customerIdLabel);


            customers = controller.getAllCustomers();
            customersBox = new JComboBox<>();
            for (Customer customer : customers)
                customersBox.addItem(customer);
            customersBox.setMaximumRowCount(5);
            this.add(customersBox);


            startDateLabel = new JLabel("Date de début :");
            startDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(startDateLabel);

            startDate = new JDatePicker();
            startDate.setShowYearButtons(true);
            startDate.getModel().setYear(2019);
            startDate.getModel().setMonth(0);
            startDate.getModel().setDay(1);
            startDate.getModel().setSelected(true);
            this.add(startDate);

            endDateLabel = new JLabel("Date de fin :");
            endDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(endDateLabel);

            endDate = new JDatePicker();
            endDate.setShowYearButtons(true);
            endDate.getModel().setYear(2020);
            endDate.getModel().setMonth(0);
            endDate.getModel().setDay(1);
            endDate.getModel().setSelected(true);
            this.add(endDate);


            takeAway = new JCheckBox("à emporter");
            takeAway.setHorizontalAlignment(SwingConstants.CENTER);
            takeAway.setSelected(true);
            this.add(takeAway);

            onSite = new JCheckBox("sur place");
            onSite.setHorizontalAlignment(SwingConstants.CENTER);
            onSite.setSelected(true);
            this.add(onSite);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public JComboBox<Customer> getCustomersBox() {
        return customersBox;
    }

    public JDatePicker getStartDate() {
        return startDate;
    }

    public JDatePicker getEndDate() {
        return endDate;
    }

    public JCheckBox getTakeAway() {
        return takeAway;
    }

    public JCheckBox getOnSite() {
        return onSite;
    }
}
