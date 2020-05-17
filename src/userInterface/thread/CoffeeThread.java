package userInterface.thread;

public class CoffeeThread extends Thread {
    private CoffeeCup parent;

    public CoffeeThread(CoffeeCup parent){
        this.parent = parent;
    }

    public void run(){
        try{
            while(true){
                for(CoffeeBean bean : parent.getCoffeeBeans())
                    bean.move();
                parent.repaint();
                Thread.sleep(100);
            }
        }
        catch (InterruptedException exception){
            exception.printStackTrace( ) ;
        }
    }
}
