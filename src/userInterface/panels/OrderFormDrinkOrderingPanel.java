package userInterface.panels;

import model.DrinkOrdering;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrderFormDrinkOrderingPanel extends JPanel {
    private JList<DrinkOrdering> orderingsList;
    private JScrollPane orderingsScrollPane;
    private JButton removeFromListButton;
    private OrderFormRecapPanel parent;
    private DefaultListModel<DrinkOrdering> listModel;

    public OrderFormDrinkOrderingPanel(OrderFormRecapPanel parent) {
        listModel = new DefaultListModel<>();

        setLayout(new BorderLayout());
        this.parent = parent;

        orderingsList = new JList<>(listModel);
        orderingsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderingsScrollPane = new JScrollPane(orderingsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(orderingsScrollPane, BorderLayout.CENTER);

        removeFromListButton = new JButton("Retirer cette boisson");
        add(removeFromListButton, BorderLayout.SOUTH);
    }

    public void addLine(DrinkOrdering orderLine) {
        listModel.addElement(orderLine);
    }

    public ArrayList<DrinkOrdering> getAllLines() {
        ArrayList<DrinkOrdering> orderings = new ArrayList<>();

        for (int i = 0; i < listModel.getSize(); i++)
            orderings.add(listModel.get(i));

        return orderings;
    }

    public double totalPrice() {
        double price = 0;

        for (int i = 0; i < listModel.getSize(); i++)
            price += listModel.get(i).price();

        return price;
    }
}
