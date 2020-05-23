package userInterface.order;

import controller.AdvantageController;
import model.Advantage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CheckoutPanel extends JPanel {
    private JLabel advantagesLabel;
    private JComboBox<Advantage> advantagesBox;
    private JCheckBox toTakeAwayBox;

    public CheckoutPanel(CheckoutFrame parent) {
        try {
            setLayout(new GridLayout(2, 2));

            AdvantageController controller = new AdvantageController();
            ArrayList<Advantage> availableAdvantages = null;

            if (parent.getParent().getBeneficiary().getLoyaltyCard() != null)
                availableAdvantages = controller.searchAdvantages(parent.getParent().getBeneficiary(), (GregorianCalendar) GregorianCalendar.getInstance(), null, 2);

            advantagesLabel = new JLabel("Vos avantages : ");
            advantagesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(advantagesLabel);

            advantagesBox = new JComboBox<>();
            advantagesBox.addItem(null);
            if (availableAdvantages != null && availableAdvantages.size() > 0)
                for (Advantage advantage : availableAdvantages)
                    if (advantage.getDiscount() < parent.getParent().getRecapPanel().totalPrice())
                        advantagesBox.addItem(advantage);

            if (advantagesBox.getModel().getSize() == 1)
                advantagesBox.setEnabled(false);
            add(advantagesBox);

            add(new JLabel(""));

            toTakeAwayBox = new JCheckBox("À emporter ?");
            add(toTakeAwayBox);

            setVisible(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(getParent(), exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isToTakeAway() {
        return toTakeAwayBox.isSelected();
    }

    public Advantage getChosenAdvantage() {
        return (Advantage) advantagesBox.getSelectedItem();
    }
}
