package userInterface.frames;

import model.Assignment;
import userInterface.panels.AssignmentsResearchForm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AssignmentResearchFrame extends JFrame {
    private Container container;
    private AssignmentsFrame assignmentsFrame;

    public AssignmentResearchFrame() {
        super("Coff-IG - services des employés");

        container = getContentPane();
        setSize(1000, 574);
        setLayout(new BorderLayout());

        add(new AssignmentsResearchForm(this));
        setVisible(true);
    }

    public void updateWindow(ArrayList<Assignment> assignments, String employeeIdentity) {
        if (assignmentsFrame == null)
            assignmentsFrame = new AssignmentsFrame(assignments, employeeIdentity);
        else assignmentsFrame.updateTable(assignments);
    }

    public void resetForm() {
        container.remove(0);
        container.add(new AssignmentsResearchForm(this));
        repaint();
        setVisible(true);
    }
}
