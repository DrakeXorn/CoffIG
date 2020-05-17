package userInterface.frames;

import model.Topping;
import model.exceptions.AllDataException;
import userInterface.tableModels.AllToppingsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllToppingsFrame extends JFrame {
    private AllToppingsModel model;
    private JTable toppingsTable;
    private JScrollPane scrollPane;
    private JButton close;
    private Container container;

    public AllToppingsFrame(ArrayList<Topping> toppings){
        super("Affichage des toppings");
        this.setBounds(600, 200, 600, 200);
        container = this.getContentPane();
        container.setLayout(new BorderLayout());

        try{
            model = new AllToppingsModel(toppings);
            toppingsTable = new JTable(model);
            toppingsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            scrollPane = new JScrollPane((toppingsTable));
            container.add(scrollPane, BorderLayout.CENTER);

            close = new JButton("Fermer la fenêtre");
            container.add(close, BorderLayout.SOUTH);
            close.addActionListener(new CloseListener());

            setVisible(true);

        } catch (AllDataException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class CloseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            AllToppingsFrame.this.dispose();
        }
    }
}
