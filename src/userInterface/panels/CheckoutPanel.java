package userInterface.panels;

import controller.OrderController;
import model.Advantage;
import model.Order;
import userInterface.frames.CheckoutFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class CheckoutPanel extends JPanel {
    private JLabel advantagesLabel;
    private JComboBox<Advantage> advantagesBox;
    private JButton orderButton;
    private JCheckBox toTakeAwayBox;
    private CheckoutFrame parent;

    public CheckoutPanel(CheckoutFrame parent) {
        this.parent = parent;

        setLayout(new GridLayout(3, 2));

        advantagesLabel = new JLabel("Vos avantages :");
        add(advantagesLabel);

        advantagesBox = new JComboBox<>();
        add(advantagesBox);

        toTakeAwayBox = new JCheckBox("À emporter ?");
        add(toTakeAwayBox);

        orderButton = new JButton("Terminer la commande");
        orderButton.addActionListener(new OrderButtonListener());
        add(orderButton);

        setVisible(true);
    }

    private class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                OrderController controller = new OrderController();
                Order order = new Order(parent.getParent().getOrderNumber(), (GregorianCalendar) GregorianCalendar.getInstance(), toTakeAwayBox.isSelected(), parent.getParent().getBeneficiary(), parent.getParent().getOrderPicker());

                order.setFoodOrderings(parent.getParent().getFoodOrderings());
                order.setDrinkOrderings(parent.getParent().getDrinkOrderings());
                controller.addOrder(order);

                parent.dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(parent, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
