package userInterface;

import model.DrinkOrdering;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AllDrinksPanel extends JPanel {
    private AllDrinksModel model;
    private JTable drinksTable;
    private JScrollPane scrollPane;

    public AllDrinksPanel(ArrayList<DrinkOrdering> drinks, AllOrdersFrame parent){
        try {
            model = new AllDrinksModel(drinks);
            drinksTable = new JTable(model);
            drinksTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            scrollPane = new JScrollPane((drinksTable));
            this.add(scrollPane, BorderLayout.CENTER);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
