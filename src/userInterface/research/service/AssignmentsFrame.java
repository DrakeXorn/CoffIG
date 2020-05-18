package userInterface.research.service;

import model.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssignmentsFrame extends JFrame {
    private JTable assignmentsTable;
    private Container container;
    private JScrollPane scrollPane;
    private JButton goBackButton;

    public AssignmentsFrame(ArrayList<Assignment> assignments, String employeeIdentity) {
        setBounds(50, 200, 700, 300);
        setLayout(new BorderLayout());
        container = getContentPane();
        updateFrame(assignments, employeeIdentity);
        goBackButton = new JButton("Retour");
        goBackButton.addActionListener(new GoBackListener());

        add(goBackButton, BorderLayout.SOUTH);
    }

    public void updateFrame(ArrayList<Assignment> assignments, String employeeIdentity) {
        setTitle("Services de " + employeeIdentity);
        if (scrollPane != null)
            container.remove(scrollPane);
        assignmentsTable = new JTable(new AssignmentsModel(assignments));
        scrollPane = new JScrollPane(assignmentsTable);
        assignmentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(scrollPane, BorderLayout.CENTER);
        repaint();
        setVisible(true);
    }

    private class GoBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
