package userInterface.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsFeatureManagementPanel extends JPanel {
    private FeaturesManagementPanel parent;
    private JButton sendToChosenButton;
    private JButton removeFromChosenButton;

    public ButtonsFeatureManagementPanel(FeaturesManagementPanel parent) {
        this.parent = parent;

        setLayout(new GridLayout(2, 1));

        sendToChosenButton = new JButton("Ajouter au café >>");
        sendToChosenButton.setPreferredSize(new Dimension(200, 15));
        sendToChosenButton.addActionListener(new AddToChosenButtonListener());
        add(sendToChosenButton);

        removeFromChosenButton = new JButton("<< Retirer du café");
        removeFromChosenButton.setPreferredSize(new Dimension(200, 15));
        removeFromChosenButton.addActionListener(new RemoveFromChosenButtonListener());
        add(removeFromChosenButton);
    }

    private class AddToChosenButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.moveToChosenList();
            parent.repaint();
        }
    }

    private class RemoveFromChosenButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.moveToList();
            parent.repaint();
        }
    }
}
