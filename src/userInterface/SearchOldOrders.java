package userInterface;

import controller.CustomerController;
import model.*;
import model.exceptions.BooleanInputException;
import model.exceptions.DateException;
import org.jdatepicker.JDatePicker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SearchOldOrders extends JPanel {
    private JLabel customerIdLabel, startDateLabel, endDateLabel;
    private ArrayList<Customer> customers;
    private JComboBox <Customer> customersBox;
    private JDatePicker startDate, endDate;
    private JCheckBox takeAway, onSite;
    private JButton search;
    private CustomerController controller;

    public SearchOldOrders(){
        controller = new CustomerController();
        this.setLayout(new GridLayout(5, 2, 5, 5));

        customerIdLabel = new JLabel("Client :");
        customerIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(customerIdLabel);

        try {
            customers = controller.getAllCustomers();
            customersBox = new JComboBox<>();
            for (Customer customer : customers)
                customersBox.addItem(customer);
            customersBox.setMaximumRowCount(3);
            this.add(customersBox);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }

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

        search = new JButton("Rechercher");
        this.add(search);
        search.addActionListener(new SearchListener());
    }

    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                GregorianCalendar endDateConverted = (GregorianCalendar)endDate.getModel().getValue();
                GregorianCalendar startDateConverted = (GregorianCalendar)startDate.getModel().getValue();
                GregorianCalendar today = (GregorianCalendar) Calendar.getInstance();

                if(startDateConverted.after(today))
                    throw new DateException(startDateConverted, "La date de début ne doit pas se trouver après aujourd'hui !");
                if(endDateConverted.after(today))
                    throw new DateException(endDateConverted, "La date de fin ne doit pas se trouver après aujourd'hui !");
                if(endDateConverted.before(startDateConverted))
                    throw new DateException(endDateConverted, "La date de fin ne doit pas se trouver avant la date de début !");
                if(!takeAway.isSelected() && !onSite.isSelected())
                    throw new BooleanInputException("Veuillez sélectionner au moins une des deux options : à emporter ou sur place");

                ArrayList<Order> orders = controller.searchOrders(customers.get(customersBox.getSelectedIndex()).getUserID(),
                        startDateConverted, endDateConverted,
                        takeAway.isSelected(), onSite.isSelected());
                if(orders.isEmpty()){
                    JOptionPane.showMessageDialog(null, "La recherche n'a trouvé aucune commande correspondant aux critères demandés",
                            "Recherche non aboutie !", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    AllOrdersFrame allOrdersFrame = new AllOrdersFrame(orders);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
