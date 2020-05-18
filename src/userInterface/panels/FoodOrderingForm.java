package userInterface.panels;

import controller.FoodController;
import model.Food;
import model.FoodOrdering;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FoodOrderingForm extends JPanel {
    private JLabel foodLabel, numberChosenLabel;
    private JComboBox<Food> foodBox;
    private JSpinner numberPiecesSpinner;
    private JButton addToListButton;
    private OrderFormCentralPanel parent;

    public FoodOrderingForm(OrderFormCentralPanel parent) {
        this.parent = parent;

        try {
            FoodController controller = new FoodController();
            setLayout(new GridLayout(2, 4));

            foodLabel = new JLabel("Nourriture souhaitée : ");
            foodLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(foodLabel);

            foodBox = new JComboBox<>();
            for (Food food : controller.getAllAvailableFoods())
                foodBox.addItem(food);
            add(foodBox);

            numberChosenLabel = new JLabel("Quantité : ");
            numberChosenLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(numberChosenLabel);

            numberPiecesSpinner = new JSpinner();
            numberPiecesSpinner.addChangeListener(new NumberChosenListener());
            add(numberPiecesSpinner);

            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel(""));


            addToListButton = new JButton();
            addToListButton.addActionListener(new AddToOrderButtonListener());
            add(addToListButton);

            setSpinnerValues();
            setPrice();
            foodBox.addActionListener(new FoodBoxListener());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetFoodList() {
        setSpinnerValues();
        addToListButton.setEnabled(true);
        repaint();
    }

    private void setSpinnerValues() {
        if (((Food) foodBox.getSelectedItem()).getStockLocation().getQuantity() != 0) {
            numberPiecesSpinner.setModel(new SpinnerNumberModel(1, 1, (int) ((Food) foodBox.getSelectedItem()).getStockLocation().getQuantity(), 1));

            numberPiecesSpinner.setEnabled(true);
            addToListButton.setEnabled(true);
        } else {
            numberPiecesSpinner.setEnabled(false);
            numberPiecesSpinner.setValue(0);
            addToListButton.setEnabled(false);
        }
    }

    private void setPrice() {
        DecimalFormat formatter = new DecimalFormat("0.00");

        formatter.setRoundingMode(RoundingMode.CEILING);
        addToListButton.setText("Ajouter à la commande (" + formatter.format((int) numberPiecesSpinner.getValue() * ((Food) foodBox.getSelectedItem()).getPrice()) + "€)");
    }

    private class FoodBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setSpinnerValues();
            setPrice();
        }
    }

    private class NumberChosenListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            setPrice();
        }
    }

    private class AddToOrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((Food) foodBox.getSelectedItem()).getStockLocation().removeNToQuantity((int) numberPiecesSpinner.getValue());
            try {
                parent.addToFoodsList(new FoodOrdering((Food) foodBox.getSelectedItem(), (int) numberPiecesSpinner.getValue(), ((Food) foodBox.getSelectedItem()).getPrice()));
                setSpinnerValues();
                setPrice();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(parent.getParent(), exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
