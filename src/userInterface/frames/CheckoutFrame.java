package userInterface.frames;

import userInterface.panels.CheckoutPanel;
import userInterface.panels.OrderForm;

import javax.swing.*;

public class CheckoutFrame extends JFrame {
    private CheckoutPanel panel;
    private OrderForm parent;

    public CheckoutFrame(OrderForm parent) {
        super("Confirmation de la commande");

        this.parent = parent;
        panel = new CheckoutPanel(this);
        add(panel);

        setVisible(true);
    }

    public OrderForm getParent() {
        return parent;
    }
}
