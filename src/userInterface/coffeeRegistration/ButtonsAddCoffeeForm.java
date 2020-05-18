package userInterface.coffeeRegistration;

import controller.StockLocationController;
import model.Coffee;
import userInterface.MainWindow;
import userInterface.utils.InputCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsAddCoffeeForm extends JPanel {
    private JButton resetButton, confirmButton;
    private MainWindow parent;

    public ButtonsAddCoffeeForm(MainWindow parent) {
        this.parent = parent;

        setLayout(new GridLayout(1, 2));

        resetButton = new JButton("Tout vider");
        resetButton.addActionListener(new ResetListener());
        add(resetButton);

        confirmButton = new JButton("Ajouter le café");
        confirmButton.addActionListener(new ConfirmListener());
        add(confirmButton);
    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.resetSize();
            parent.getWindowContainer().removeAll();
            parent.getWindowContainer().add(new CoffeeForm(parent, null), BorderLayout.CENTER);
            parent.getWindowContainer().add(new ButtonsAddCoffeeForm(parent), BorderLayout.SOUTH);
            parent.repaint();
            parent.setVisible(true);
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
                        if (JOptionPane.showConfirmDialog(parent, coffee, "Confirmer l'ajout ?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            StockLocationController stockController = new StockLocationController();
                            stockController.updateStockLocation(coffee.getStockLocation());

                            form.getController().addCoffee(coffee);
                            parent.goBackHome();
                        }
                    } else
                        JOptionPane.showMessageDialog(parent, "Vous devez choisir un emplacement qui n'est pas encore utilisé !", "Attention", JOptionPane.WARNING_MESSAGE);
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(parent, exception.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
