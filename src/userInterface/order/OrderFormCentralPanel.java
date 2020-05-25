package userInterface.order;

import model.DrinkOrdering;
import model.FoodOrdering;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OrderFormCentralPanel extends JPanel {
    private DrinkOrderingForm drinksPanel;
    private FoodOrderingForm foodsPanel;
    private OrderForm parent;

    public OrderFormCentralPanel(OrderForm parent) {
        Border drinksTitleBorder = BorderFactory.createTitledBorder("Boissons");
        Border foodsTitleBorder = BorderFactory.createTitledBorder("Nourritures");

        setLayout(new GridLayout(2, 1));
        this.parent = parent;

        drinksPanel = new DrinkOrderingForm(this);
        drinksPanel.setBorder(drinksTitleBorder);
        add(drinksPanel);

        foodsPanel = new FoodOrderingForm(this);
        foodsPanel.setBorder(foodsTitleBorder);
        add(foodsPanel);
    }

    public void addToDrinksList(DrinkOrdering drinkOrdering) {
        parent.addToDrinksList(drinkOrdering);
    }

    public int getDrinksListSize() {
        return parent.getDrinkOrderings().size();
    }

    public void addToFoodsList(FoodOrdering foodOrdering) {
        parent.addToFoodsList(foodOrdering);
    }

    public void resetFoodList() {
        foodsPanel.resetFoodList();
    }
}
