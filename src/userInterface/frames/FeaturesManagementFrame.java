package userInterface.frames;

import userInterface.panels.ButtonsFeatureManagementPanel;
import userInterface.panels.CoffeeForm;
import userInterface.panels.FeaturesManagementPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FeaturesManagementFrame extends JFrame {
    private Container container;
    private CoffeeForm parent;
    private FeaturesManagementPanel featuresManagementPanel;
    private JButton confirmButton;

    public FeaturesManagementFrame(CoffeeForm parent) {
        super("Ajout/retrait de caractéristiques");

        setSize(785, 170);
        setLayout(new BorderLayout());
        this.parent = parent;
        container = getContentPane();

        featuresManagementPanel = new FeaturesManagementPanel(this);
        container.add(featuresManagementPanel, BorderLayout.CENTER);

        confirmButton = new JButton("Terminer et revenir à l'ajout du café");
        confirmButton.addActionListener(new ConfirmButtonListener());
        container.add(confirmButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public FeaturesManagementPanel getFeaturesManagementPanel() {
        return featuresManagementPanel;
    }

    public CoffeeForm getParent() {
        return parent;
    }

    private class ConfirmButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.setFeatures(featuresManagementPanel.getChosenFeatures());
            dispose();
        }
    }
}
