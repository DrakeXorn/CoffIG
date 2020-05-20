package userInterface.research.order;

import com.github.lgooddatepicker.components.DatePicker;
import controller.CustomerController;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SearchOldOrdersForm extends JPanel {
    private JLabel customerIdLabel, startDateLabel, endDateLabel;
    private ArrayList<Customer> customers;
    private JComboBox <Customer> customersBox;
    private DatePicker startDate, endDate;
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

            startDate = new DatePicker();
            startDate.setDate(LocalDate.of(2019, Month.JANUARY, 1));

            this.add(startDate);

            endDateLabel = new JLabel("Date de fin :");
            endDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.add(endDateLabel);

            endDate = new DatePicker();
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

    public DatePicker getStartDate() {
        return startDate;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public JCheckBox getTakeAway() {
        return takeAway;
    }

    public JCheckBox getOnSite() {
        return onSite;
    }
}
