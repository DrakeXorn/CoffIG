package userInterface.order;

import model.FoodOrdering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderFormFoodOrderingsPanel extends JPanel {
    private JList<FoodOrdering> orderingsList;
    private JScrollPane orderingsScrollPane;
    private JButton removeFromListButton;
    private OrderFormRecapPanel parent;
    private DefaultListModel<FoodOrdering> listModel;

    public OrderFormFoodOrderingsPanel(OrderFormRecapPanel parent) {
        listModel = new DefaultListModel<>();
        this.parent = parent;

        setLayout(new BorderLayout());

        orderingsList = new JList<>(listModel);
        orderingsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderingsScrollPane = new JScrollPane(orderingsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(orderingsScrollPane, BorderLayout.CENTER);

        removeFromListButton = new JButton("Retirer cet aliment");
        removeFromListButton.addActionListener(new RemoveFromListListener());
        add(removeFromListButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void addLine(FoodOrdering orderLine) {
        if (listModel.contains(orderLine)) {
            int i = 0;

            while (i < listModel.size() && !listModel.get(i).equals(orderLine))
                i++;

            listModel.get(i).addPieces(orderLine.getNbrPieces());
            orderingsList.repaint();
        } else
            listModel.addElement(orderLine);
    }

    public ArrayList<FoodOrdering> getAllLines() {
        ArrayList<FoodOrdering> orderings = new ArrayList<>();

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

    private class RemoveFromListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (orderingsList.getSelectedValue() != null) {
                orderingsList.getSelectedValue().getFood().getStockLocation().addNToQuantity(orderingsList.getSelectedValue().getNbrPieces());
                listModel.removeElement(orderingsList.getSelectedValue());
                parent.resetFoodList();
                orderingsList.repaint();
                parent.setButtonText();
            } else
                JOptionPane.showMessageDialog(parent.getParent(), "Vous devez choisir une ligne pour la retirer !", "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
