package userInterface.coffeeRegistration;

import controller.CoffeeController;
import controller.StockLocationController;
import model.Coffee;
import userInterface.MainWindow;
import userInterface.utils.InputCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsUpdateCoffeeForm extends JPanel {
    private JButton goBackButton, confirmButton;
    private MainWindow parent;

    public ButtonsUpdateCoffeeForm(MainWindow parent) {
        this.parent = parent;

        setLayout(new GridLayout(1, 2));

        goBackButton = new JButton("Retour");
        goBackButton.addActionListener(new ResetListener());
        add(goBackButton);

        confirmButton = new JButton("Modifier le café");
        confirmButton.addActionListener(new ConfirmListener());
        add(confirmButton);
    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.getWindowContainer().removeAll();
            UpdateCoffeesFrame frame = new UpdateCoffeesFrame(parent);
            parent.repaint();
        }
    }

    private class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CoffeeForm form = ((CoffeeForm) parent.getWindowContainer().getComponent(0));
            if (!InputCheck.areInputsFilled(form.getCoffeeID(), form.getWeightNeeded(), form.getPrice(), form.getPackaging(), form.getAlley(), form.getShelf(), form.getNumber(), form.getCountries(), form.getIntensity(), form.getExpirationDatePicker(), form.getQuantityBought()))
                JOptionPane.showMessageDialog(parent, "Vous devez remplir tous les champs obligatoires !", "Attention", JOptionPane.ERROR_MESSAGE);
            else {
                try {
                    Coffee coffee = form.createCoffee();
                    if (coffee != null) {
                        if (form.getFeatures() != null)
                            coffee.setFeatures(form.getFeatures());
                        if (JOptionPane.showConfirmDialog(parent, coffee, "Confirmer la modification ?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            CoffeeController coffeeController = new CoffeeController();
                            StockLocationController stockLocationController = new StockLocationController();
                            coffeeController.updateCoffee(coffee);
                            stockLocationController.updateStockLocation(coffee.getStockLocation());
                            parent.goBackHome();
                        }
                    } else
                        JOptionPane.showMessageDialog(parent, "Vous devez choisir un emplacement qui n'est pas encore utilisé !", "Attention", JOptionPane.WARNING_MESSAGE);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(parent, exception.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
