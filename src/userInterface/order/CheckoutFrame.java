package userInterface.order;

import controller.OrderController;
import model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class CheckoutFrame extends JFrame {
    private CheckoutPanel checkoutPanel;
    private JButton confirmOrderButton;
    private Container container;
    private OrderForm parent;

    public CheckoutFrame(OrderForm parent) {
        super("Confirmation de la commande");

        setSize(600, 200);
        this.parent = parent;

        setLayout(new BorderLayout());
        container = getContentPane();

        checkoutPanel = new CheckoutPanel();
        container.add(checkoutPanel, BorderLayout.CENTER);

        confirmOrderButton = new JButton("Terminer la commande");
        confirmOrderButton.addActionListener(new OrderButtonListener());
        container.add(confirmOrderButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public OrderForm getParent() {
        return parent;
    }

    private class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                OrderController controller = new OrderController();
                Order order = new Order(parent.getOrderNumber(), (GregorianCalendar) GregorianCalendar.getInstance(), checkoutPanel.isToTakeAway(), parent.getBeneficiary(), parent.getOrderPicker());

                order.setFoodOrderings(parent.getFoodOrderings());
                order.setDrinkOrderings(parent.getDrinkOrderings());
                controller.addOrder(order);

                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(parent, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
