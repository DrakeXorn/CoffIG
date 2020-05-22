package userInterface.order;

import controller.DrinkController;
import model.Drink;
import model.DrinkOrdering;
import model.Topping;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class DrinkOrderingForm extends JPanel {
    private JLabel drinkLabel, numberChosenLabel, sizeLabel;
    private JComboBox<Drink> drinkBox;
    private JComboBox<String> sizeBox;
    private JSpinner numberSpinner;
    private JButton addToListButton, manageToppingsButton;
    private OrderFormCentralPanel parent;
    private ArrayList<Topping> toppings;

    public DrinkOrderingForm(OrderFormCentralPanel parent) {
        this.parent = parent;
        toppings = new ArrayList<>();

        try {
            DrinkController controller = new DrinkController();
            setLayout(new GridLayout(3, 4));

            drinkLabel = new JLabel("Boisson souhaitée : ");
            drinkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(drinkLabel);

            drinkBox = new JComboBox<>();
            for (Drink drink : controller.getAllDrinks())
                drinkBox.addItem(drink);
            add(drinkBox);

            numberChosenLabel = new JLabel("Quantité : ");
            numberChosenLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(numberChosenLabel);

            numberSpinner = new JSpinner();
            numberSpinner.addChangeListener(new NumberChosenListener());
            add(numberSpinner);

            sizeLabel = new JLabel("Taille voulue : ");
            sizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(sizeLabel);

            sizeBox = new JComboBox<>();
            sizeBox.addItem("Small");
            sizeBox.addItem("Medium");
            sizeBox.addItem("Large");
            sizeBox.addActionListener(new SizeBoxListener());
            add(sizeBox);

            add(new JLabel(""));

            manageToppingsButton = new JButton("Ajouter des suppléments");
            manageToppingsButton.addActionListener(new ManageToppingsButtonListener());
            add(manageToppingsButton);

            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel(""));

            addToListButton = new JButton("Ajouter à la liste");
            addToListButton.addActionListener(new AddToOrderButtonListener());
            add(addToListButton);

            drinkBox.addActionListener(new DrinkBoxListener());
            resetSpinnerState();
            updatePrice();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetSpinnerState() {
        numberSpinner.setModel(new SpinnerNumberModel(1, 1, 50 - parent.getDrinksListSize(), 1));
    }

    private Double getSizePrice() {
        return switch (sizeBox.getSelectedIndex()) {
            case 1 -> 4.5;
            case 2 -> 7.;
            default -> 3.5;
        };
    }

    private Double getLinePrice() {
        double totalToppings = 0;

        for (Topping topping : toppings)
            totalToppings += topping.getPrice();
        return Math.floor((((((Drink) Objects.requireNonNull(drinkBox.getSelectedItem())).getCoffee().getPrice() / ((Drink) drinkBox.getSelectedItem()).getCoffee().getPackaging()) * ((Drink) drinkBox.getSelectedItem()).getCoffee().getWeightNeededForPreparation()) + getSizePrice() + totalToppings) * 100) / 100;
    }

    public void updatePrice() {
        addToListButton.setText("Ajouter à la commande (" + getLinePrice() + "€)");
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    private class DrinkBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sizeBox.setSelectedIndex(0);
            resetSpinnerState();
            updatePrice();
        }
    }

    private class NumberChosenListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            updatePrice();
        }
    }

    private class SizeBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatePrice();
        }
    }

    private class ManageToppingsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ToppingsManagementFrame frame = new ToppingsManagementFrame(DrinkOrderingForm.this);
        }
    }

    private class AddToOrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                parent.addToDrinksList(new DrinkOrdering((Drink) drinkBox.getSelectedItem(), ((String) Objects.requireNonNull(sizeBox.getSelectedItem())).toLowerCase(), (int) numberSpinner.getValue(), getLinePrice(), new ArrayList<>(toppings)));
                toppings.clear();
                resetSpinnerState();
                updatePrice();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(DrinkOrderingForm.this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
