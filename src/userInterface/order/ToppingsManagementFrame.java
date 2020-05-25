package userInterface.order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToppingsManagementFrame extends JFrame {
    private ToppingsManagementPanel toppingsPanel;
    private JButton acceptButton;
    private DrinkOrderingForm parent;
    private Container container;

    public ToppingsManagementFrame(DrinkOrderingForm parent) {
        super("Ajout/retrait de suppléments");

        setSize(800, 500);
        this.parent = parent;
        setLayout(new BorderLayout());
        container = getContentPane();

        toppingsPanel = new ToppingsManagementPanel(this);
        container.add(toppingsPanel, BorderLayout.CENTER);

        acceptButton = new JButton("Accepter");
        acceptButton.addActionListener(new AcceptButtonListener());
        container.add(acceptButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public DrinkOrderingForm getParent() {
        return parent;
    }

    private class AcceptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.setToppings(toppingsPanel.getToppings());
            parent.resetSpinnerState();
            parent.updatePrice();
            dispose();
        }
    }
}
