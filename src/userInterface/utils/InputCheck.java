package userInterface.utils;


import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class InputCheck {
    public static boolean areInputsFilled(JComponent... fields) {
        AtomicInteger nbrEmpty = new AtomicInteger(0);
        ArrayList<JComponent> components = new ArrayList<>(Arrays.asList(fields));

        components.forEach((component) -> {
            if ((component instanceof DatePicker && ((DatePicker)component).getComponentDateTextField().getText().isEmpty())
                    || (component instanceof JComboBox<?> && Objects.equals(((JComboBox<?>) component).getSelectedItem(), ""))
                    || (component instanceof JTextField && ((JTextField)component).getText().isEmpty())
                    || (component instanceof JSpinner && (Integer)((JSpinner)component).getValue() == 0)) {
                component.setBorder(BorderFactory.createLineBorder(Color.RED));
                nbrEmpty.getAndIncrement();
            } else if (component != null && component.getBorder() != null && !component.getBorder().equals(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"))) {
                component.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }
        });

        return nbrEmpty.get() == 0;
    }
}
