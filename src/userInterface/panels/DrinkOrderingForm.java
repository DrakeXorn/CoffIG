package userInterface.panels;

import controller.DrinkController;
import model.Drink;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DrinkOrderingForm extends JPanel {
    private JLabel drinkLabel, numberChosenLabel, sizeLabel;
    private JComboBox<Drink> drinkBox;
    private JComboBox<String> sizeBox;
    private JSpinner numberSpinner;
    private JButton addToListButton;
    private OrderFormCentralPanel parent;

    public DrinkOrderingForm(OrderFormCentralPanel parent) {
        this.parent = parent;

        try {
            DrinkController controller = new DrinkController();
            setLayout(new GridLayout(2, 4));

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
            add(numberSpinner);

            sizeLabel = new JLabel("Taille voulue : ");
            sizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(sizeLabel);

            sizeBox = new JComboBox<>();
            sizeBox.addItem("Small");
            sizeBox.addItem("Medium");
            sizeBox.addItem("Large");
            add(sizeBox);

            add(new JLabel(""));

            addToListButton = new JButton("Ajouter à la liste");
            addToListButton.addActionListener(new AddToOrderButtonListener());
            add(addToListButton);

            resetSpinnerState();
            drinkBox.addActionListener(new DrinkBoxListener());
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetSpinnerState() {
        numberSpinner.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
    }

    private void setPrice() {
        DecimalFormat formatter = new DecimalFormat("0.00");

        formatter.setRoundingMode(RoundingMode.CEILING);
        addToListButton.setText("Ajouter à la commande (" + formatter.format((int) numberSpinner.getValue() * ((Drink) drinkBox.getSelectedItem()).getCoffee().getPrice()) + "€)");
    }

    private class DrinkBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetSpinnerState();
            setPrice();
        }
    }

    private class AddToOrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //parent.addToDrinksList(new DrinkOrdering((Drink) drinkBox.getSelectedItem(), (int) numberSpinner.getValue(), sizeButtonGroup.getSelection()., ((Drink) drinkBox.getSelectedItem()).getCoffee().getPrice()));
                resetSpinnerState();
                setPrice();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(DrinkOrderingForm.this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
