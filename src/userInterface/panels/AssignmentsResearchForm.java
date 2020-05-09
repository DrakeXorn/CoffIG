package userInterface.panels;

import controller.AssignmentController;
import controller.EmployeeController;
import model.Assignment;
import model.Employee;
import model.exceptions.AllDataException;
import org.jdatepicker.JDatePicker;
import userInterface.frames.AssignmentResearchFrame;
import userInterface.utils.InputCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AssignmentsResearchForm extends JPanel {
    private AssignmentResearchFrame parent;
    private JLabel employeesBoxLabel, startDateLabel, durationLabel;
    private JComboBox<String> employeesBox, durationBox;
    private JDatePicker startDatePicker;
    private JButton resetButton, acceptButton;

    public AssignmentsResearchForm(AssignmentResearchFrame parent) {
        try {
            this.parent = parent;
            setLayout(new GridLayout(4, 2));

            EmployeeController controller = new EmployeeController();
            ArrayList<Employee> employees = controller.getAllEmployees();

            employeesBoxLabel = new JLabel("Employé : ");
            startDateLabel = new JLabel("Date de début : ");
            durationLabel = new JLabel("Durée voulue : ");
            employeesBox = new JComboBox<>();
            durationBox = new JComboBox<>();
            resetButton = new JButton("Remettre à zéro");
            acceptButton = new JButton("Confirmer");

            resetButton.addActionListener(new ResetButtonListener());
            acceptButton.addActionListener(new AcceptButtonListener());

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
            add(resetButton);
            add(acceptButton);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur lors de la récupération des employés", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.resetForm();
        }
    }

    private class AcceptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (InputCheck.areInputsFilled(employeesBox, startDatePicker)) {
                    AssignmentController controller = new AssignmentController();
                    GregorianCalendar endDate = (GregorianCalendar) ((GregorianCalendar) startDatePicker.getModel().getValue()).clone();
                    ArrayList<Assignment> assignments;

                    endDate.add(GregorianCalendar.DATE, switch (durationBox.getSelectedIndex()) {
                        case 1 -> 15;
                        case 2 -> 31;
                        default -> 7;
                    });

                    assignments = controller.searchAssignments((String) employeesBox.getSelectedItem(), (GregorianCalendar) startDatePicker.getModel().getValue(), endDate);

                    if (!assignments.isEmpty())
                        parent.updateWindow(assignments, (String) employeesBox.getSelectedItem());
                    else {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

                        JOptionPane.showMessageDialog(AssignmentsResearchForm.this, "Aucun service attribué pour " + employeesBox.getSelectedItem() + " entre le " + dateFormat.format(((GregorianCalendar) startDatePicker.getModel().getValue()).getTime()) + " et le " + dateFormat.format(endDate.getTime()) + ".", "Aucun service", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(AssignmentsResearchForm.this, "Vous devez remplir tous les champs !", "Erreur d'encodage", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(AssignmentsResearchForm.this, exception.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
