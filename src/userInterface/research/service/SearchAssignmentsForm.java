package userInterface.research.service;

import controller.EmployeeController;
import model.Employee;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchAssignmentsForm extends JPanel {
    private JLabel employeesBoxLabel, startDateLabel, durationLabel;
    private JComboBox<String> employeesBox, durationBox;
    private JDatePicker startDatePicker;

    public SearchAssignmentsForm() {
        try {
            setLayout(new GridLayout(3, 2));

            EmployeeController controller = new EmployeeController();
            ArrayList<Employee> employees = controller.getAllEmployees();

            employeesBoxLabel = new JLabel("Employé : ");
            startDateLabel = new JLabel("Date de début : ");
            durationLabel = new JLabel("Durée voulue : ");
            employeesBox = new JComboBox<>();
            durationBox = new JComboBox<>();

            employeesBox.addItem("");
            for (Employee employee : employees) {
                employeesBox.addItem(employee.getIdentity());
            }

            durationBox.addItem("1 semaine");
            durationBox.addItem("15 jours");
            durationBox.addItem("1 mois (31 jours)");

            startDatePicker = new JDatePicker();

            employeesBoxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            startDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            durationLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            add(employeesBoxLabel);
            add(employeesBox);
            add(startDateLabel);
            add(startDatePicker);
            add(durationLabel);
            add(durationBox);

            setVisible(true);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur lors de la récupération des employés", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JComboBox<String> getEmployeesBox() {
        return employeesBox;
    }

    public JComboBox<String> getDurationBox() {
        return durationBox;
    }

    public JDatePicker getStartDatePicker() {
        return startDatePicker;
    }
}
