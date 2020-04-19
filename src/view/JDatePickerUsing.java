package view;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.util.Properties;

public class JDatePickerUsing extends JPanel {
    private JDatePickerImpl datePicker;

    public JDatePickerUsing() {
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        JDatePanelImpl datePanel;

        properties.put("text.today", "aujourd'hui");
        properties.put("text.month", "mois");
        properties.put("text.year", "année");

        datePanel = new JDatePanelImpl(model, properties);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
    }
}
