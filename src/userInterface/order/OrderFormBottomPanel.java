package userInterface.order;

import controller.EmployeeController;
import model.Employee;
import model.exceptions.*;

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
    private Employee orderPicker;

    public OrderFormBottomPanel() throws ConnectionException, StringInputException, CharacterInputException, DateException, AllDataException, ClosedShopException {
            EmployeeController controller = new EmployeeController();
            ArrayList<Employee> currentlyWorkingEmployees = controller.getCurrentlyWorkingEmployees();

            DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");

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

            if (currentlyWorkingEmployees.size() == 0)
                throw new ClosedShopException();
            Random random = new Random(System.currentTimeMillis());
            orderPicker = currentlyWorkingEmployees.get(currentlyWorkingEmployees.size() > 1 ? random.nextInt(currentlyWorkingEmployees.size()) : 0);

            willBeServedByField = new JTextField();
            willBeServedByField.setText(orderPicker.getIdentity());
            willBeServedByField.setEditable(false);

            add(willBeServedByField);

            add(new JLabel(""));
            add(new JLabel(""));
    }

    public Employee getOrderPicker() {
        return orderPicker;
    }
}
