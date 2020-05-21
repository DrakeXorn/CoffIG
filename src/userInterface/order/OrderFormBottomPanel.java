package userInterface.order;

import controller.EmployeeController;
import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

public class OrderFormBottomPanel extends JPanel {
    private JLabel dateLabel, willBeServedByLabel;
    private JTextField dateField, willBeServedByField;
    private OrderForm parent;
    private Employee orderPicker;

    public OrderFormBottomPanel(OrderForm parent) {
        try {
            EmployeeController controller = new EmployeeController();
            ArrayList<Employee> currentlyWorkingEmployees = controller.getCurrentlyWorkingEmployees();

            DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            this.parent = parent;

            setLayout(new GridLayout());

            dateLabel = new JLabel("Date : ");
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(dateLabel);

            dateField = new JTextField(dateFormat.format(GregorianCalendar.getInstance().getTime()));
            dateField.setEditable(false);
            add(dateField);
            add(new JLabel(""));

            willBeServedByLabel = new JLabel("Vous serez servi par : ");
            willBeServedByLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(willBeServedByLabel);

            Random random = new Random(System.currentTimeMillis());
            orderPicker = currentlyWorkingEmployees.get(currentlyWorkingEmployees.size() > 1 ? random.nextInt( currentlyWorkingEmployees.size()) : 0);

            willBeServedByField = new JTextField();
            willBeServedByField.setText(orderPicker.getIdentity());
            willBeServedByField.setEditable(false);

            add(willBeServedByField);

            add(new JLabel(""));
            add(new JLabel(""));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Employee getOrderPicker() {
        return orderPicker;
    }
}
