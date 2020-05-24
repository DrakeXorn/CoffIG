package userInterface.order;

import model.DrinkOrdering;
import model.FoodOrdering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OrderFormRecapPanel extends JPanel {
    private OrderFormDrinkOrderingsPanel drinkOrderingPanel;
    private OrderFormFoodOrderingsPanel foodOrderingPanel;
    private JButton orderButton;
    private OrderForm parent;

    public OrderFormRecapPanel(OrderForm parent) {
        this.parent = parent;
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createTitledBorder("Récapitulatif de la commande"));

        drinkOrderingPanel = new OrderFormDrinkOrderingsPanel(this);
        add(drinkOrderingPanel);

        foodOrderingPanel = new OrderFormFoodOrderingsPanel(this);
        add(foodOrderingPanel);

        orderButton = new JButton();
        orderButton.addActionListener(new OrderButtonListener());
        setButtonText();
        orderButton.setPreferredSize(new Dimension(200, 50));
        add(orderButton);
    }

    public double totalPrice() {
        return Math.floor((foodOrderingPanel.totalPrice() + drinkOrderingPanel.totalPrice()) * 100) / 100;
    }

    public void setButtonText() {
        orderButton.setText("Commander (" + totalPrice() + "€)");
    }

    public void addToDrinksList(DrinkOrdering drinkOrdering) {
        drinkOrderingPanel.addLine(drinkOrdering);
        setButtonText();
    }

    public void addToFoodsList(FoodOrdering foodOrdering) {
        foodOrderingPanel.addLine(foodOrdering);
        setButtonText();
    }

    public void resetFoodList() {
        parent.resetFoodList();
    }

    public ArrayList<DrinkOrdering> getDrinkOrderings() {
        return drinkOrderingPanel.getAllLines();
    }

    public ArrayList<FoodOrdering> getFoodOrderings() {
        return foodOrderingPanel.getAllLines();
    }

    private class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (drinkOrderingPanel.getAllLines().size() != 0 || foodOrderingPanel.getAllLines().size() != 0) {
                new CheckoutFrame(parent);
            } else
                JOptionPane.showMessageDialog(parent, "Vous devez avoir au moins une ligne de commande pour effectuer une commande !", "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
