package userInterface.thread;

import userInterface.panels.MainPanel;

public class CoffeeThread extends Thread {
    private MainPanel parent;

    public CoffeeThread(MainPanel parent){
        this.parent = parent;
    }

    public void run(){
        try{
            while(true){
                for(CoffeeBean drop : parent.getCoffeeBeans())
                    drop.move();
                parent.repaint();
                Thread.sleep(80);
            }
        }
        catch (InterruptedException exception){
            exception.printStackTrace( ) ;
        }
    }
}
