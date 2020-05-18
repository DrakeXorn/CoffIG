package userInterface.research.advantage;

import controller.AdvantageController;
import model.Advantage;
import model.exceptions.AllDataException;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AllAdvantagesFrame extends JFrame{
    private JTable advantagesTable;
    private JScrollPane scrollPane;
    private Container container;
    private AdvantageController controller;
    private AllAdvantagesModel model;

    public AllAdvantagesFrame(ArrayList<Advantage> advantages) {
        super("Affichage des avantages rechercher");
        setBounds(50, 200, 700, 300);
        setLayout(new BorderLayout());
        container = this.getContentPane();
        controller = new AdvantageController();

        try {
            model = new AllAdvantagesModel(advantages);
            advantagesTable = new JTable(model);
            advantagesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            scrollPane = new JScrollPane((advantagesTable));
            container.add(scrollPane, BorderLayout.CENTER);
            setVisible(true);
        }
        catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateFrame(ArrayList<Advantage> advantages) throws AllDataException {
        if (scrollPane != null)
            container.remove(scrollPane);
        advantagesTable = new JTable(new AllAdvantagesModel(advantages));
        scrollPane = new JScrollPane(advantagesTable);
        add(scrollPane, BorderLayout.CENTER);
        repaint();
        setVisible(true);
    }
}
