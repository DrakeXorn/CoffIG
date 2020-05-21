package userInterface.order;

import model.Customer;
import model.DrinkOrdering;
import model.Employee;
import model.FoodOrdering;
import model.exceptions.*;
import userInterface.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrderForm extends JPanel {
    private OrderFormTopPanel topBar;
    private OrderFormRecapPanel recapPanel;
    private OrderFormCentralPanel centralPanel;
    private OrderFormBottomPanel bottomBar;
    private MainWindow parent;

    public OrderForm(MainWindow parent) throws AllDataException, ConnectionException, ClosedShopException, StringInputException, DateException, CharacterInputException {
        this.parent = parent;

        setLayout(new BorderLayout());

        topBar = new OrderFormTopPanel(this);
        centralPanel = new OrderFormCentralPanel(this);
        bottomBar = new OrderFormBottomPanel(this);
        recapPanel = new OrderFormRecapPanel(this);

        add(topBar, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
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
