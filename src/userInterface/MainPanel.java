package userInterface;

import controller.OrderController;
import userInterface.order.OrderForm;
import userInterface.thread.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    private CoffeeCup cup;
    private CoffeeThread thread;
    private JButton newOrder;
    private MainWindow parent;

    public MainPanel(MainWindow parent) {
        this.setLayout(new BorderLayout());

        this.parent = parent;

        cup = new CoffeeCup();
        this.add(cup, BorderLayout.CENTER);

        thread = new CoffeeThread(cup);
        thread.start();

        newOrder = new JButton("Commander");
        newOrder.setBackground(new Color(255, 255, 255));
        newOrder.setForeground(new Color(10, 161, 231));
        newOrder.setFont(new Font("Arial", Font.BOLD, 30));
        newOrder.setPreferredSize(new Dimension(100, 50));
        this.add(newOrder, BorderLayout.SOUTH);
        newOrder.addActionListener(new NewOrderListener());
    }

    private class NewOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                parent.getWindowContainer().removeAll();
                parent.getWindowContainer().add(new OrderForm(parent));
                parent.repaint();
                parent.setVisible(true);
                parent.setSize(1200, 500);
                repaint();

                // getAllAdvantages (selon cardId et période validité) pour les afficher dans un JCombobox
                //String message = controller.addPointsToLoyaltyCard(cardId, orderPrice);
                //String message = controller.removePointsToLoyaltyCard(cardId, points);
                //controller.updateStockLocation(alley, shelf, number, removeQuantity);
                //boolean isEmptyStock = controller.isEmptyStockLocation(alley, shelf, number);
                //controller.removeRight(loyaltyCardId, advantageId);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
                parent.goBackHome();
            }
        }
    }
}
