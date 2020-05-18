package userInterface.thread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CoffeeBean {
    private final static int goBack = 0;
    private final static int limit = 315;
    private final static int range = 60;
    private final static int min = 360;
    private Image coffeeBean;
    private Rectangle placement;
    private int deltaY;
    private CoffeeCup parent;

    public CoffeeBean(int y, int width, int height, int deltaY, CoffeeCup parent){
        placement = new Rectangle(xRandom(range, min), y, width, height);
        this.deltaY = deltaY;
        this.parent = parent;
    }

    public void draw(Graphics g){
        try {
           coffeeBean = ImageIO.read(ClassLoader.getSystemResource("coffeeBean.png"));
            g.drawImage(coffeeBean, placement.x, placement.y, placement.width, placement.height, parent);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int xRandom(int range, int min){
        return (int)(Math.random() * range + min);
    }

    public void move(){
        placement.y += deltaY;
        if(placement.y >= limit) {
            placement.x = xRandom(range, min);
            placement.y = goBack;
        }
    }
}
