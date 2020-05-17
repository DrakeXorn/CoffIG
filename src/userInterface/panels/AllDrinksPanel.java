package userInterface.panels;

import model.DrinkOrdering;
import model.Topping;
import userInterface.tableModels.AllDrinksModel;
import userInterface.frames.AllToppingsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllDrinksPanel extends JPanel {
    private AllDrinksModel model;
    private JTable drinksTable;
    private JScrollPane scrollPane;
    private ListSelectionModel listSelect;
    private JButton showToppings;

    public AllDrinksPanel(ArrayList<DrinkOrdering> drinks){
        try {
            model = new AllDrinksModel(drinks);
            drinksTable = new JTable(model);
            drinksTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            drinksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = drinksTable.getSelectionModel();

            scrollPane = new JScrollPane((drinksTable));
            this.add(scrollPane, BorderLayout.CENTER);

            showToppings = new JButton("Afficher les toppings");
            this.add(showToppings);
            showToppings.addActionListener(new ButtonListener());

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(!listSelect.isSelectionEmpty()){
                DrinkOrdering drinkOrderingToDisplay = model.getRow(listSelect.getMinSelectionIndex());
                ArrayList<Topping> toppings = drinkOrderingToDisplay.getToppings();

                if(!toppings.isEmpty()) {
                    AllToppingsFrame toppingsFrame = new AllToppingsFrame(toppings);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Sélectionnez une boisson à afficher !",
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
