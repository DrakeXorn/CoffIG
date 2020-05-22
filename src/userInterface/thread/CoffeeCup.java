package userInterface.thread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoffeeCup extends JPanel {
    private ArrayList<CoffeeBean> coffeeBeansArray;
    private List<CoffeeBean> coffeeBeans;

    public CoffeeCup(){
        this.setBackground(new Color(207, 233, 255));

        coffeeBeansArray = new ArrayList<>();
        coffeeBeans = Collections.synchronizedList(coffeeBeansArray);

        coffeeBeans.add(new CoffeeBean(80, this));
        coffeeBeans.add(new CoffeeBean(180, this));
        coffeeBeans.add(new CoffeeBean(280, this));
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
