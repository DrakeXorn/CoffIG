package userInterface.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsManagementPanel extends JPanel {
    private ManagementPanel parent;
    private JButton sendToChosenButton;
    private JButton removeFromChosenButton;

    public ButtonsManagementPanel(ManagementPanel parent) {
        this.parent = parent;

        setLayout(new GridLayout(2, 1));

        sendToChosenButton = new JButton("Ajouter >>");
        sendToChosenButton.setPreferredSize(new Dimension(200, 15));
        sendToChosenButton.setBackground(new Color(165, 214, 167));
        sendToChosenButton.addActionListener(new AddToChosenButtonListener());
        add(sendToChosenButton);

        removeFromChosenButton = new JButton("<< Retirer");
        removeFromChosenButton.setPreferredSize(new Dimension(200, 15));
        removeFromChosenButton.setBackground(new Color(229, 115, 115));
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
