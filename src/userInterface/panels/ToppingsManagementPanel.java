package userInterface.panels;

import controller.ToppingController;
import model.Topping;
import userInterface.frames.ToppingsManagementFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToppingsManagementPanel extends JPanel implements ManagementPanel {
    private JList<Topping> toppingsList;
    private JList<Topping> chosenToppingsList;
    private JScrollPane toppingsScrollPane, chosenToppingsScrollPane;
    private ButtonsManagementPanel buttonsPanel;
    private ArrayList<Topping> toppings;
    private DefaultListModel<Topping> toppingsModel;
    private DefaultListModel<Topping> chosenToppingsModel;
    private ToppingsManagementFrame parent;

    public ToppingsManagementPanel(ToppingsManagementFrame parent) {
        try {
            ToppingController controller = new ToppingController();
            toppings = controller.getAllAvailableToppings();
            this.parent = parent;
            setLayout(new GridLayout(1, 3));

            toppingsModel = new DefaultListModel<>();
            for (Topping topping : toppings)
                toppingsModel.addElement(topping);

            toppingsList = new JList<>(toppingsModel);
            toppingsScrollPane = new JScrollPane(toppingsList);
            toppingsScrollPane.setBorder(BorderFactory.createTitledBorder("Suppléments disponibles"));
            add(toppingsScrollPane);

            buttonsPanel = new ButtonsManagementPanel(this);
            add(buttonsPanel);

            chosenToppingsModel = new DefaultListModel<>();
            /*for (Topping topping : parent.getParent().getToppings())
                chosenToppingsModel.addElement(topping);*/

            chosenToppingsList = new JList<>(chosenToppingsModel);
            chosenToppingsScrollPane = new JScrollPane(chosenToppingsList);
            chosenToppingsScrollPane.setBorder(BorderFactory.createTitledBorder("Suppléments choisis"));
            add(chosenToppingsScrollPane);

            setVisible(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(parent.getParent(), exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setToppings() {
        //parent.setToppings();
    }

    @Override
    public void moveToList() {
        for (Topping topping : chosenToppingsList.getSelectedValuesList()) {
            toppingsModel.addElement(topping);
            chosenToppingsModel.removeElement(topping);
        }
    }

    @Override
    public void moveToChosenList() {
        for (Topping topping : toppingsList.getSelectedValuesList()) {
            chosenToppingsModel.addElement(topping);
            toppingsModel.removeElement(topping);
        }
    }
}
