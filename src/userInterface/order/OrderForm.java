package userInterface.order;

import model.Customer;
import model.DrinkOrdering;
import model.Employee;
import model.FoodOrdering;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrderForm extends JPanel {
    private OrderFormTopPanel topBar;
    private OrderFormRecapPanel recapPanel;
    private OrderFormCentralPanel centralPanel;
    private OrderFormBottomPanel bottomBar;

    public OrderForm() {
        setLayout(new BorderLayout());
        topBar = new OrderFormTopPanel(this);
        add(topBar, BorderLayout.NORTH);

        centralPanel = new OrderFormCentralPanel(this);
        add(centralPanel, BorderLayout.CENTER);

        bottomBar = new OrderFormBottomPanel(this);
        add(bottomBar, BorderLayout.SOUTH);

        recapPanel = new OrderFormRecapPanel(this);
        add(recapPanel, BorderLayout.EAST);
    }

    public Integer getOrderNumber() {
        return topBar.getOrderNumber();
    }

    public Customer getBeneficiary() {
        return topBar.getBeneficiary();
    }

    public Employee getOrderPicker() {
        return bottomBar.getOrderPicker();
    }

    public void addToDrinksList(DrinkOrdering drinkOrdering) {
        recapPanel.addToDrinksList(drinkOrdering);
    }

    public void addToFoodsList(FoodOrdering foodOrdering) {
        recapPanel.addToFoodsList(foodOrdering);
    }

    public void resetFoodList() {
        centralPanel.resetFoodList();
    }

    public ArrayList<FoodOrdering> getFoodOrderings() {
        return recapPanel.getFoodOrderings();
    }

    public ArrayList<DrinkOrdering> getDrinkOrderings() {
        return recapPanel.getDrinkOrderings();
    }
}
