package userInterface.panels;

import controller.OrderController;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;
import userInterface.frames.MainWindow;
import userInterface.thread.CoffeeBean;
import userInterface.thread.CoffeeThread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Collections;

public class MainPanel extends JPanel {
    private final static int range = 60;
    private final static int min = 370;
    private final static int width = 20;
    private final static int height = 20;
    private final static int deltaY = 10;
    private ArrayList<CoffeeBean> coffeeBeansArray = new ArrayList<>();
    private List<CoffeeBean> coffeeBeans = Collections.synchronizedList(coffeeBeansArray);
    private CoffeeThread thread;
    private Image coffeeCup;
    private JButton newOrder;

    public MainPanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(207, 233, 255));

        coffeeBeans.add(new CoffeeBean(xRandom(range, min), 80, width, height, deltaY, this));
        coffeeBeans.add(new CoffeeBean(xRandom(range, min), 180, width, height, deltaY, this));
        coffeeBeans.add(new CoffeeBean(xRandom(range, min), 280, width, height, deltaY, this));

        thread = new CoffeeThread(this);
        thread.start();

        newOrder = new JButton("Passer une commande");
        this.add(newOrder, BorderLayout.SOUTH);
        newOrder.addActionListener(new NewOrderListener());
    }

    public List<CoffeeBean> getCoffeeBeans() {
        return coffeeBeans;
    }

    public int xRandom(int range, int min){
        return (int)(Math.random() * range + min);
    }

    public void paint (Graphics g) {
        super.paint(g);
        try {
            coffeeCup = ImageIO.read(ClassLoader.getSystemResource("coffeeCup.png"));
            g.drawImage(coffeeCup, 280, 200, 300, 300, this);

            for(CoffeeBean bean : coffeeBeans)
                bean.draw(g);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class NewOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // charger le form de la commande
                // getAllAdvantages (selon cardId et période validité) pour les afficher dans un JCombobox

                OrderController controller = new OrderController();

                // valeurs à récupérer du formulaire
                String cardId = "0495505955";
                double orderPrice = 5.25;
                int points = 50;

                //String message = controller.addPointsToLoyaltyCard(cardId, orderPrice);
                //String message = controller.removePointsToLoyaltyCard(cardId, points);
                boolean isEmptyStockLocation =  controller.updateStockLocation(1, 2, 1, 2);

                JOptionPane.showMessageDialog(null,
                        isEmptyStockLocation ? "Le stock est vide" : "Quantité encore disponible",
                        "Update stock", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
