package userInterface.order;

import model.DrinkOrdering;
import model.Topping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderFormDrinkOrderingsPanel extends JPanel {
    private JList<DrinkOrdering> orderingsList;
    private JScrollPane orderingsScrollPane;
    private JButton removeFromListButton;
    private OrderFormRecapPanel parent;
    private DefaultListModel<DrinkOrdering> listModel;

    public OrderFormDrinkOrderingsPanel(OrderFormRecapPanel parent) {
        listModel = new DefaultListModel<>();

        setLayout(new BorderLayout());
        this.parent = parent;

        orderingsList = new JList<>(listModel);
        orderingsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderingsList.setCellRenderer(new DrinkOrderingsListCellRenderer());
        orderingsList.setFixedCellWidth(300);
        orderingsScrollPane = new JScrollPane(orderingsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(orderingsScrollPane, BorderLayout.CENTER);

        removeFromListButton = new JButton("Retirer cette boisson");
        removeFromListButton.addActionListener(new RemoveFromListListener());
        add(removeFromListButton, BorderLayout.SOUTH);
    }

    public void addLine(DrinkOrdering orderLine) {
        if (listModel.contains(orderLine)) {
            int i = 0;

            while (i < listModel.size() && !listModel.get(i).equals(orderLine))
                i++;

            listModel.get(i).addPieces(orderLine.getNbrPieces());
            orderingsList.repaint();
        } else
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
            price += listModel.get(i).getPrice();

        return price;
    }

    private class DrinkOrderingsListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            StringBuilder cellValue = new StringBuilder("<html><body><p>" + ((DrinkOrdering) value).getNbrPieces() + " * " + ((DrinkOrdering) value).getDrink() + " (" + ((DrinkOrdering) value).getSize() + ")</p>");

            for (Topping topping : ((DrinkOrdering) value).getToppings())
                cellValue.append("<p style='padding-left:10px'>+ ").append(topping).append("</p>");
            cellValue.append("<p>Total de la ligne : ").append(Math.floor(((DrinkOrdering) value).getPrice() * 100) / 100).append("€</p></body></html>");

            renderer.setText(cellValue.toString());
            return renderer;
        }
    }

    private class RemoveFromListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (orderingsList.getSelectedValue() != null) {
                for (Topping topping : orderingsList.getSelectedValue().getToppings())
                    topping.getStockLocation().addNToQuantity(orderingsList.getSelectedValue().getNbrPieces());
                listModel.removeElement(orderingsList.getSelectedValue());
                orderingsList.repaint();
                parent.setButtonText();
            } else
                JOptionPane.showMessageDialog(parent.getParent(), "Vous devez choisir une ligne pour la retirer !", "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
