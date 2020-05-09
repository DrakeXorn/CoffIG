package userInterface;

import model.DrinkOrdering;
import model.FoodOrdering;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AllFoodsPanel extends JPanel{
    private AllFoodsModel model;
    private JTable foodsTable;
    private JScrollPane scrollPane;

    public AllFoodsPanel(ArrayList<FoodOrdering> foods, AllOrdersFrame parent){
        try {
            model = new AllFoodsModel(foods);
            foodsTable = new JTable(model);
            foodsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            scrollPane = new JScrollPane((foodsTable));
            this.add(scrollPane, BorderLayout.CENTER);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
