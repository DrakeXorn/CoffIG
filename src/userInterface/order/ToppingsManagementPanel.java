package userInterface.order;

import controller.ToppingController;
import model.Topping;
import userInterface.ButtonsManagementPanel;
import userInterface.ManagementPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ToppingsManagementPanel extends JPanel implements ManagementPanel {
    private JList<Topping> toppingsList;
    private JList<Topping> chosenToppingsList;
    private JScrollPane toppingsScrollPane, chosenToppingsScrollPane;
    private ButtonsManagementPanel buttonsPanel;
    private DefaultListModel<Topping> toppingsModel;
    private DefaultListModel<Topping> chosenToppingsModel;
    private static ArrayList<Topping> toppings;

    public ToppingsManagementPanel(ToppingsManagementFrame parent) {
        try {
            ToppingController controller = new ToppingController();
            if (toppings == null)
                toppings = controller.getAllAvailableToppings();
            setLayout(new GridLayout(1, 3));

            toppingsModel = new DefaultListModel<>();
            for (Topping topping : toppings)
                if (topping.getStockLocation().getQuantity() > 0)
                    toppingsModel.addElement(topping);

            toppingsList = new JList<>(toppingsModel);
            toppingsScrollPane = new JScrollPane(toppingsList);
            toppingsScrollPane.setBorder(BorderFactory.createTitledBorder("Suppléments disponibles"));
            add(toppingsScrollPane);

            buttonsPanel = new ButtonsManagementPanel(this);
            add(buttonsPanel);

            chosenToppingsModel = new DefaultListModel<>();
            for (Topping topping : parent.getParent().getToppings()) {
                toppingsModel.removeElement(topping);
                chosenToppingsModel.addElement(topping);
            }

            chosenToppingsList = new JList<>(chosenToppingsModel);
            chosenToppingsScrollPane = new JScrollPane(chosenToppingsList);
            chosenToppingsScrollPane.setBorder(BorderFactory.createTitledBorder("Suppléments choisis"));
            add(chosenToppingsScrollPane);

            setVisible(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(parent.getParent(), exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Topping> getToppings() {
        ArrayList<Topping> toppings = new ArrayList<>();

        for (int iTopping = 0; iTopping < chosenToppingsModel.getSize(); iTopping++)
            toppings.add(chosenToppingsModel.get(iTopping));

        toppings.sort(Comparator.comparingInt(topping -> topping.getToppingID()));
        return toppings;
    }

    @Override
    public void moveToList() {
        for (Topping topping : chosenToppingsList.getSelectedValuesList()) {
            topping.getStockLocation().addNToQuantity(1);
            toppingsModel.addElement(topping);
            chosenToppingsModel.removeElement(topping);
        }
    }

    @Override
    public void moveToChosenList() {
        for (Topping topping : toppingsList.getSelectedValuesList()) {
            chosenToppingsModel.addElement(topping);
            topping.getStockLocation().removeNToQuantity(1);
            toppingsModel.removeElement(topping);
        }
    }
}
