package userInterface.panels;

import controller.AdvantageController;
import model.Advantage;
import model.exceptions.AllDataException;
import userInterface.frames.AllAdvantagesFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchAdvantagesPanel extends JPanel {
    private SearchAdvantagesForm form;
    private JButton searchButton;
    private AllAdvantagesFrame allAdvantagesFrame;
    private Boolean isFirstWindow;

    public SearchAdvantagesPanel() {
        this.setLayout(new BorderLayout());

        isFirstWindow = true;

        form = new SearchAdvantagesForm();
        searchButton = new JButton("Rechercher les Avantages");

        this.add(form, BorderLayout.CENTER);
        this.add(searchButton, BorderLayout.SOUTH);
        searchButton.addActionListener(new SearchAdvantagesPanel.SearchListener());
        setVisible(true);
    }

    public void updateWindow(ArrayList<Advantage> advantages) throws AllDataException {
        if (allAdvantagesFrame == null)
            allAdvantagesFrame = new AllAdvantagesFrame(advantages);
        else allAdvantagesFrame.updateFrame(advantages);
    }

    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (form.getCustomers().get(form.getCustomersBox().getSelectedIndex()).getLoyaltyCard() != null) {
                try {
                    AdvantageController controller = new AdvantageController();

                    ArrayList<Advantage> advantages = controller.searchAdvantages(
                            form.getCustomers().get(form.getCustomersBox().getSelectedIndex()),
                            form.getToday(),
                            form.getDiscounts().get(form.getDiscountsBox().getSelectedIndex()),
                            form.getTypeAdvantage()
                    );

                    if (advantages.size() != 0) {
                        if (isFirstWindow) {
                            allAdvantagesFrame = new AllAdvantagesFrame(advantages);
                            isFirstWindow = false;
                        }
                        else updateWindow(advantages);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Aucun avantage ne correspond aux critères de recherche !",
                                "Attention !", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(),
                            "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Ce client n'a pas de carte de fidélité !",
                        "Attention !", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
