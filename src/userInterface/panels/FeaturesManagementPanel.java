package userInterface.panels;

import controller.FeatureController;
import userInterface.frames.FeaturesManagementFrame;
import userInterface.utils.InputCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FeaturesManagementPanel extends JPanel {
    private JLabel addFeatureLabel;
    private JTextField addFeatureField;
    private JButton addFeatureButton;
    private JList<String> featuresList;
    private JList<String> chosenFeaturesList;
    private ButtonsFeatureManagementPanel buttonsPanel;
    private ArrayList<String> features;
    private DefaultListModel<String> featuresModel;
    private DefaultListModel<String> chosenFeaturesModel;

    public FeaturesManagementPanel(FeaturesManagementFrame parent) {
        try {
            FeatureController controller = new FeatureController();
            features = controller.getFeatures();

            setLayout(new GridLayout(2, 3));

            addFeatureLabel = new JLabel("Nouvelle caractéristique : ");
            addFeatureLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(addFeatureLabel);

            addFeatureField = new JTextField();
            add(addFeatureField);

            addFeatureButton = new JButton("Ajouter");
            addFeatureButton.addActionListener(new AddFeatureButtonListener());
            add(addFeatureButton);

            featuresModel = new DefaultListModel<>();
            for (String feature : features)
                featuresModel.addElement(feature);

            featuresList = new JList<>(featuresModel);
            featuresList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            add(new JScrollPane(featuresList));

            buttonsPanel = new ButtonsFeatureManagementPanel(this);
            add(buttonsPanel, BorderLayout.SOUTH);

            chosenFeaturesModel = new DefaultListModel<>();
            if (parent.getParent().getFeatures() != null)
                for (String feature : parent.getParent().getFeatures()) {
                    chosenFeaturesModel.addElement(feature);
                    featuresModel.removeElement(feature);
                }

            chosenFeaturesList = new JList<>(chosenFeaturesModel);
            chosenFeaturesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            add(new JScrollPane(chosenFeaturesList));

            setVisible(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur lors de la récupération des caractéristiques", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<String> getChosenFeatures() {
        ArrayList<String> features = new ArrayList<>();

        for (int i = 0; i < chosenFeaturesModel.getSize(); i++)
            features.add(chosenFeaturesModel.getElementAt(i));
        
        return features;
    }

    public void moveToChosenList() {
        for (String feature : featuresList.getSelectedValuesList()) {
            chosenFeaturesModel.addElement(feature);
            featuresModel.removeElement(feature);
        }
    }

    public void moveToList() {
        for (String feature : chosenFeaturesList.getSelectedValuesList()) {
            featuresModel.addElement(feature);
            chosenFeaturesModel.removeElement(feature);
        }
    }

    private class AddFeatureButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (InputCheck.areInputsFilled(addFeatureField)) {
                if (featuresModel.contains(addFeatureField.getText().toLowerCase()))
                    featuresModel.removeElement(addFeatureField.getText().toLowerCase());
                chosenFeaturesModel.addElement(addFeatureField.getText().toLowerCase());
                addFeatureField.setText("");
            } else
                JOptionPane.showMessageDialog(FeaturesManagementPanel.this, "Vous devez remplir la zone de texte pour ajouter une caractéristique", "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
