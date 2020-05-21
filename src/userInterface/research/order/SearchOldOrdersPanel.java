package userInterface.research.order;

import controller.OrderController;
import model.*;
import model.exceptions.BooleanInputException;
import model.exceptions.DateException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SearchOldOrdersPanel extends JPanel {
    private SearchOldOrdersForm form;
    private JButton search;

    public SearchOldOrdersPanel(){
        this.setLayout(new BorderLayout());

        form = new SearchOldOrdersForm();
        this.add(form, BorderLayout.CENTER);

        search = new JButton("Rechercher");
        this.add(search, BorderLayout.SOUTH);
        search.addActionListener(new SearchListener());
    }

    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                OrderController controller = new OrderController();
                GregorianCalendar endDateConverted = new GregorianCalendar();
                GregorianCalendar startDateConverted = new GregorianCalendar();
                GregorianCalendar today = (GregorianCalendar)Calendar.getInstance();

                endDateConverted.setTime(Date.from(form.getEndDate().getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                startDateConverted.setTime(Date.from(form.getStartDate().getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                if(startDateConverted.after(today))
                    throw new DateException(startDateConverted, "La date de début ne doit pas se trouver après aujourd'hui !");
                if(endDateConverted.after(today))
                    throw new DateException(endDateConverted, "La date de fin ne doit pas se trouver après aujourd'hui !");
                if(endDateConverted.before(startDateConverted))
                    throw new DateException(endDateConverted, "La date de fin ne doit pas se trouver avant la date de début !");
                if(!form.getTakeAway().isSelected() && !form.getOnSite().isSelected())
                    throw new BooleanInputException("Veuillez sélectionner au moins une des deux options : à emporter ou sur place");

                Customer customer = form.getCustomers().get(form.getCustomersBox().getSelectedIndex());
                ArrayList<Order> orders = controller.searchOrders(customer.getUserID(),
                        startDateConverted, endDateConverted,
                        form.getTakeAway().isSelected(), form.getOnSite().isSelected());
                if(orders.isEmpty()){
                    JOptionPane.showMessageDialog(null, "La recherche n'a trouvé aucune commande correspondant aux critères demandés",
                            "Recherche non aboutie !", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    AllOrdersFrame allOrdersFrame = new AllOrdersFrame(orders, customer);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
