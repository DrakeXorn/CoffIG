package userInterface.thread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoffeeCup extends JPanel {
    private final static int width = 20;
    private final static int height = 20;
    private final static int deltaY = 1;
    private ArrayList<CoffeeBean> coffeeBeansArray = new ArrayList<>();
    private List<CoffeeBean> coffeeBeans = Collections.synchronizedList(coffeeBeansArray);

    public CoffeeCup(){
        this.setBackground(new Color(207, 233, 255));
        coffeeBeans.add(new CoffeeBean(80, width, height, deltaY, this));
        coffeeBeans.add(new CoffeeBean(180, width, height, deltaY, this));
        coffeeBeans.add(new CoffeeBean(280, width, height, deltaY, this));
    }

    public List<CoffeeBean> getCoffeeBeans() {
        return coffeeBeans;
    }

    public void paint (Graphics g) {
        super.paint(g);
        try {
            Image coffeeCup = ImageIO.read(ClassLoader.getSystemResource("coffeeCup.png"));
            g.drawImage(coffeeCup, 330, 290, 180, 180, this);

            for(CoffeeBean bean : coffeeBeans)
                bean.draw(g);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
