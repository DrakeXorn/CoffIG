package userInterface.thread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CoffeeBean {
    private final static int INITIAL_Y = 0;
    private final static int LIMIT = 330;
    private final static int RANGE = 60;
    private final static int MINIMUM = 360;
    private Image coffeeBean;
    private Rectangle placement;
    private int deltaY;
    private CoffeeCup parent;

    public CoffeeBean(int y, int width, int height, int deltaY, CoffeeCup parent) {
        placement = new Rectangle(randomXPos(RANGE, MINIMUM), y, width, height);
        this.deltaY = deltaY;
        this.parent = parent;
    }

    public void draw(Graphics g) {
        try {
           coffeeBean = ImageIO.read(ClassLoader.getSystemResource("coffeeBean.png"));
            g.drawImage(coffeeBean, placement.x, placement.y, placement.width, placement.height, parent);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int randomXPos(int range, int min){
        return (int)(Math.random() * range + min);
    }

    public void move() {
        placement.y += deltaY;
        if (placement.y >= LIMIT) {
            placement.x = randomXPos(RANGE, MINIMUM);
            placement.y = INITIAL_Y;
        }
    }
}
