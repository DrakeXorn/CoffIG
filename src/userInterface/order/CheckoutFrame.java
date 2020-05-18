package userInterface.order;

import userInterface.order.CheckoutPanel;
import userInterface.order.OrderForm;

import javax.swing.*;
import java.awt.*;

public class CheckoutFrame extends JFrame {
    private CheckoutPanel panel;
    private Container container;
    private OrderForm parent;

    public CheckoutFrame(OrderForm parent) {
        super("Confirmation de la commande");

        setSize(600, 450);
        this.parent = parent;

        container = getContentPane();

        panel = new CheckoutPanel(this);
        container.add(panel);

        setVisible(true);
    }

    public OrderForm getParent() {
        return parent;
    }
}
