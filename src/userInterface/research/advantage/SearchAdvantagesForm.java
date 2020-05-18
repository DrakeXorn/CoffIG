package userInterface.research.advantage;

import controller.AdvantageController;
import controller.CustomerController;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SearchAdvantagesForm extends JPanel {
    private JLabel customerIdLabel, dateOfDaysLabel, discountLabel;
    private ArrayList<Customer> customers;
    private JTextField dateOfDays;
    private JComboBox customersBox, discount;
    private JRadioButton anyAdvantage, accessibleAdvantage, inaccessibleAdvantage;
    private ButtonGroup buttonGroup;
    private CustomerController customerController;
    private AdvantageController advantageController;
    private GregorianCalendar today;
    private DateFormat dateFormat;
    private Date date;

    public SearchAdvantagesForm() {
        try {
            this.setLayout(new GridLayout(2, 1, 5, 5));

            JPanel panel1 = new JPanel();
            panel1.setLayout(new GridLayout(3, 2, 5, 5));

            JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayout(1, 3, 5, 5));

            customerController = new CustomerController();
            advantageController = new AdvantageController();
            advantageController.getAllAdvantageDiscount();



            customerIdLabel = new JLabel("Client :");
            customerIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            customers = customerController.getAllCustomers();
            customersBox = new JComboBox<>();
            for (Customer customer : customers) customersBox.addItem(customer);
            customersBox.setMaximumRowCount(5);



            dateOfDaysLabel = new JLabel("Date du jour : ");
            dateOfDaysLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            today = (GregorianCalendar) Calendar.getInstance();
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = today.getTime();

            dateOfDays = new JTextField(dateFormat.format(date));
            dateOfDays.setEditable(false);



            discountLabel = new JLabel("Remise : ");
            discountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            discount = new JComboBox<>();
            discount.setMaximumRowCount(5);

            for (Double val : advantageController.getDiscounts()) discount.addItem(val);




            anyAdvantage = new JRadioButton("Tous les avantages", true);
            anyAdvantage.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(anyAdvantage);
            accessibleAdvantage = new JRadioButton("Avantages accessibles", false);
            accessibleAdvantage.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(accessibleAdvantage);
            inaccessibleAdvantage = new JRadioButton("Avantages inaccessibles", false);
            inaccessibleAdvantage.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(inaccessibleAdvantage);

            buttonGroup = new ButtonGroup();
            buttonGroup.add(anyAdvantage);
            buttonGroup.add(accessibleAdvantage);
            buttonGroup.add(inaccessibleAdvantage);



            panel1.add(customerIdLabel);
            panel1.add(customersBox);
            panel1.add(dateOfDaysLabel);
            panel1.add(dateOfDays);
            panel1.add(discountLabel);
            panel1.add(discount);
            this.add(panel1, BorderLayout.NORTH);

            panel2.add(anyAdvantage);
            panel2.add(accessibleAdvantage);
            panel2.add(inaccessibleAdvantage);
            this.add(panel2, BorderLayout.CENTER);


            setVisible(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(),
                    "Erreur lors de la récupération des remise", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Customer> getCustomers() { return customers; }
    public JComboBox<Customer> getCustomersBox() { return customersBox; }
    public GregorianCalendar getToday() { return today; }
    public ArrayList<Double> getDiscounts() { return advantageController.getDiscounts(); }
    public JComboBox getDiscountsBox() { return discount; }
    public int getTypeAdvantage() { return anyAdvantage.isSelected() ? 1 : accessibleAdvantage.isSelected() ? 2 : 3; }
}
