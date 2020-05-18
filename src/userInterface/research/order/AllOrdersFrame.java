package userInterface.research.order;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllOrdersFrame extends JFrame {
    private AllOrdersModel model;
    private JTable ordersTable;
    private JScrollPane scrollPane;
    private ListSelectionModel listSelect;
    private JButton select, goBack;
    private Container container;
    private ArrayList<Order> orders;
    private Customer customer;

    public AllOrdersFrame(ArrayList<Order> orders, Customer customer){
        super("Recherche des anciennes commandes de/d' " + customer);
        this.customer = customer;
        this.setBounds(90, 90, 800, 400);
        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        this.orders = orders;

        try {
            model = new AllOrdersModel(orders);
            ordersTable = new JTable(model);
            ordersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            ordersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = ordersTable.getSelectionModel();

            scrollPane = new JScrollPane((ordersTable));
            container.add(scrollPane, BorderLayout.CENTER);

            select = new JButton("Afficher la commande sélectionnée");
            container.add(select, BorderLayout.SOUTH);
            select.addActionListener(new SelectListener());

            setVisible(true);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Container getContainer() {
        return container;
    }

    private class SelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(!listSelect.isSelectionEmpty()){
                Order orderToDisplay = model.getRow(listSelect.getMinSelectionIndex());
                container.removeAll();
                JTabbedPane tabbedPane = new JTabbedPane();

                ArrayList<DrinkOrdering> drinkOrderings = orderToDisplay.getDrinkOrderings();
                if(!drinkOrderings.isEmpty())
                    tabbedPane.insertTab("Boisson(s)", null, new AllDrinksPanel(drinkOrderings), "Détail des boissons", 0);

                ArrayList<FoodOrdering> foodOrderings = orderToDisplay.getFoodOrderings();
                if(!foodOrderings.isEmpty())
                    tabbedPane.insertTab("Nourriture(s)", null, new AllFoodsPanel(foodOrderings), "Détail de la nourriture", 1);

                container.add(tabbedPane, BorderLayout.CENTER);

                goBack = new JButton("Retour");
                goBack.addActionListener(new GoBackListener());
                container.add(goBack, BorderLayout.SOUTH);

                container.repaint();
                setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Sélectionnez une commande à afficher !",
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class GoBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            AllOrdersFrame allOrdersFrame = new AllOrdersFrame(orders, customer);
        }
    }
}
