package userInterface.frames;

import controller.EmployeeController;
import userInterface.tableModels.AllEmployeesModel;

import javax.swing.*;
import java.awt.*;

public class AllEmployeesFrame extends JFrame {
    private AllEmployeesModel model;
    private JTable empleyeeTable;
    private JScrollPane scrollPane;
    private EmployeeController controller;
    private MainWindow mainWindow;
    private Container container;

    public AllEmployeesFrame(MainWindow window) {
        super("Affichage de tous les employés");
        this.setBounds(50, 50, 1400, 400);
        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        mainWindow = window;
        controller = new EmployeeController();

        try {
            model = new AllEmployeesModel(controller.getAllEmployees());
            empleyeeTable = new JTable(model);
            empleyeeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            scrollPane = new JScrollPane((empleyeeTable));
            container.add(scrollPane, BorderLayout.CENTER);
            setVisible(true);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
