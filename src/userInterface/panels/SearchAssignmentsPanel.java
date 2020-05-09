package userInterface.panels;

import controller.AssignmentController;
import model.Assignment;
import userInterface.frames.AssignmentsFrame;
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

public class SearchAssignmentsPanel extends JPanel {
    private SearchAssignmentsForm form;
    private AssignmentsFrame assignmentsFrame;
    private JButton acceptButton;

    public SearchAssignmentsPanel() {
        setLayout(new BorderLayout());

        form = new SearchAssignmentsForm();
        acceptButton = new JButton("Rechercher les services");

        this.add(form, BorderLayout.CENTER);
        this.add(acceptButton, BorderLayout.SOUTH);
        acceptButton.addActionListener(new AcceptButtonListener());
        setVisible(true);
    }

    public void updateWindow(ArrayList<Assignment> assignments, String employeeIdentity) {
        if (assignmentsFrame == null)
            assignmentsFrame = new AssignmentsFrame(assignments, employeeIdentity);
        else assignmentsFrame.updateFrame(assignments, employeeIdentity);
    }

    private class AcceptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (InputCheck.areInputsFilled(form.getEmployeesBox(), form.getStartDatePicker())) {
                    AssignmentController controller = new AssignmentController();
                    GregorianCalendar endDate = (GregorianCalendar) ((GregorianCalendar) form.getStartDatePicker().getModel().getValue()).clone();
                    ArrayList<Assignment> assignments;

                    endDate.add(GregorianCalendar.DATE, switch (form.getDurationBox().getSelectedIndex()) {
                        case 1 -> 15;
                        case 2 -> 31;
                        default -> 7;
                    });

                    assignments = controller.searchAssignments((String) form.getEmployeesBox().getSelectedItem(), (GregorianCalendar) form.getStartDatePicker().getModel().getValue(), endDate);

                    if (!assignments.isEmpty())
                        updateWindow(assignments, (String) form.getEmployeesBox().getSelectedItem());
                    else {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

                        JOptionPane.showMessageDialog(SearchAssignmentsPanel.this, "Aucun service attribué pour " + form.getEmployeesBox().getSelectedItem() + " entre le " + dateFormat.format(((GregorianCalendar) form.getStartDatePicker().getModel().getValue()).getTime()) + " et le " + dateFormat.format(endDate.getTime()) + ".", "Aucun service", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(SearchAssignmentsPanel.this, "Vous devez remplir tous les champs !", "Erreur d'encodage", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(SearchAssignmentsPanel.this, exception.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
