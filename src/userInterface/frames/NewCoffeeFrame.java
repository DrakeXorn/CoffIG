package userInterface.frames;

import userInterface.panels.NewCoffeeForm;

import javax.swing.*;
import java.awt.*;

public class NewCoffeeFrame extends JFrame {
    private Container container;

    public NewCoffeeFrame() {
        super("Nouveau café");

        container = getContentPane();
        container.setLayout(new BorderLayout());
        setBounds(250, 300, 500, 400);
        container.add(new NewCoffeeForm(this));

        setVisible(true);
    }

    public void resetForm() {
        container.removeAll();
        container.add(new NewCoffeeForm(this));
        repaint();
        setVisible(true);
    }
}
