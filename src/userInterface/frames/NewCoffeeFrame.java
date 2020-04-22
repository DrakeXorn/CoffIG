package userInterface.frames;

import userInterface.panels.NewCoffeeForm;

import javax.swing.*;
import java.awt.*;

public class NewCoffeeFrame extends JFrame {
    private Container container;

    public NewCoffeeFrame() {
        container = getContentPane();

        container.setLayout(new BorderLayout());
        setTitle("Nouveau café");
        setBounds(250, 300, 500, 400);
        container.add(new NewCoffeeForm());

        this.setVisible(true);
    }
}
