package userInterface.coffeeRegistration;

import model.Coffee;
import userInterface.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCoffeesFrame extends JFrame {
    private Container container;
    private MainWindow parent;
    private AllCoffeesPanel coffeesPanel;
    private JButton updateButton;

    public UpdateCoffeesFrame(MainWindow parent) {
        super("Modifier un café");

        setLayout(new BorderLayout());
        container = getContentPane();
        container.setLayout(new BorderLayout());
        setBounds(250, 300, 1250, 600);

        this.parent = parent;
        coffeesPanel = new AllCoffeesPanel(this);
        container.add(coffeesPanel, BorderLayout.CENTER);

        updateButton = new JButton("Modifier le café");
        updateButton.addActionListener(new UpdateButtonListener());
        container.add(updateButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Coffee chosenCoffee = coffeesPanel.getChosenCoffee();

            if (chosenCoffee != null) {
                parent.resetSize();
                parent.getWindowContainer().removeAll();
                CoffeeForm coffeeForm = new CoffeeForm(chosenCoffee);
                parent.getWindowContainer().add(coffeeForm, BorderLayout.CENTER);
                parent.getWindowContainer().add(new ButtonsUpdateCoffeeForm(parent), BorderLayout.SOUTH);

                parent.getWindowContainer().repaint();
                parent.setVisible(true);
                dispose();
            } else
                JOptionPane.showMessageDialog(null, "Sélectionnez un café à modifier !",
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
