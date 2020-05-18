package userInterface.order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToppingsManagementFrame extends JFrame {
    private ToppingsManagementPanel panel;
    private JButton returnButton;
    private DrinkOrderingForm parent;
    private Container container;

    public ToppingsManagementFrame(DrinkOrderingForm parent) {
        super("Ajout/retrait de suppléments");

        setSize(800, 500);
        this.parent = parent;
        setLayout(new BorderLayout());
        container = getContentPane();

        panel = new ToppingsManagementPanel(this);
        container.add(panel, BorderLayout.CENTER);

        returnButton = new JButton("Accepter");
        container.add(returnButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public DrinkOrderingForm getParent() {
        return parent;
    }

    private class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
