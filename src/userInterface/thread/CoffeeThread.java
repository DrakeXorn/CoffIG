package userInterface.thread;

import javax.swing.*;

public class CoffeeThread extends Thread {
    private CoffeeCup parent;

    public CoffeeThread(CoffeeCup parent) {
        this.parent = parent;
    }

    public void run() {
        try {
            while (true) {
                for(CoffeeBean bean : parent.getCoffeeBeans())
                    bean.move();
                parent.repaint();
                Thread.sleep(9);
            }
        } catch (InterruptedException exception){
            JOptionPane.showMessageDialog(null,  "Erreur lors de l'animation", "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
