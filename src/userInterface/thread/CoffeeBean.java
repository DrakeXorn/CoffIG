package userInterface.thread;

import dataAccess.SingletonConnection;
import userInterface.panels.MainPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CoffeeBean {
    private final static int goBack = 0;
    private final static int limit = 315;
    private Image coffeeBean;
    private Rectangle placement;
    private int deltaY;
    private MainPanel parent;

    public CoffeeBean(int x, int y, int width, int height, int deltaY, MainPanel parent){
        placement = new Rectangle(x, y, width, height);
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

    public void move(){
        placement.y += deltaY;
        if(placement.y >= limit)
            placement.y = goBack;
    }
}
