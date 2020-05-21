package userInterface.order;

import model.Advantage;

import javax.swing.*;
import java.awt.*;

public class CheckoutPanel extends JPanel {
    private JLabel advantagesLabel;
    private JComboBox<Advantage> advantagesBox;
    private JCheckBox toTakeAwayBox;
    private CheckoutFrame parent;

    public CheckoutPanel(CheckoutFrame parent) {
        this.parent = parent;

        setLayout(new GridLayout(2, 2));

        advantagesLabel = new JLabel("Vos avantages :");
        advantagesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(advantagesLabel);

        advantagesBox = new JComboBox<>();
        add(advantagesBox);

        add(new JLabel(""));

        toTakeAwayBox = new JCheckBox("À emporter ?");
        add(toTakeAwayBox);

        setVisible(true);
    }

    public boolean isToTakeAway() {
        return toTakeAwayBox.isSelected();
    }
}
