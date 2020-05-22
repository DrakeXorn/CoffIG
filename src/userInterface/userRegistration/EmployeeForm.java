package userInterface.userRegistration;

import com.github.lgooddatepicker.components.DatePicker;
import controller.EmployeeController;
import controller.UserController;
import model.*;
import model.exceptions.DateException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;

public class EmployeeForm extends JPanel {
    private JLabel hireDateLabel, endContractDateLabel, discountLabel;
    private DatePicker hireDatePicker, endContractDatePicker;
    private JCheckBox isEmployeeOfMonth, wantsParkingSpace, wantsEndContract;
    private JSpinner discount;
    private UserForm userInfos;

    public EmployeeForm(UserForm userInfos) {
        this.setLayout(new GridLayout(5, 2, 5, 5));
        this.userInfos = userInfos;

        hireDateLabel = new JLabel("Date d'embauche* :");
        hireDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(hireDateLabel);

        hireDatePicker = new DatePicker();
        hireDatePicker.setDateToToday();
        this.add(hireDatePicker);

        this.add(new JLabel(""));

        wantsEndContract = new JCheckBox("Choisir une date de fin de contrat");
        wantsEndContract.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(wantsEndContract);
        wantsEndContract.addItemListener(new WantsEndContractDateListener());

        endContractDateLabel = new JLabel("Date de fin de contrat :");
        endContractDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(endContractDateLabel);

        endContractDatePicker = new DatePicker();
        endContractDatePicker.setEnabled(false);
        this.add(endContractDatePicker);

        discountLabel = new JLabel("Remise* :");
        discountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(discountLabel);
        discount = new JSpinner(new SpinnerNumberModel(0, 0, 100., 10.));
        this.add(discount);

        isEmployeeOfMonth = new JCheckBox("Je suis employé du mois");
        isEmployeeOfMonth.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(isEmployeeOfMonth);

        wantsParkingSpace = new JCheckBox("Je souhaite posséder un emplacement de parking");
        wantsParkingSpace.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(wantsParkingSpace);
    }

    private class WantsEndContractDateListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            endContractDatePicker.setEnabled(event.getStateChange() == ItemEvent.SELECTED);
        }
    }

    public Employee createEmployee() {
        Employee employee = null;
        try {
            EmployeeController employeeController = new EmployeeController();
            UserController userController =  new UserController();
            GregorianCalendar hireDate = new GregorianCalendar();
            GregorianCalendar endContractDate = new GregorianCalendar();

            hireDate.setTime(Date.from(hireDatePicker.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            if (wantsEndContract.isSelected() && endContractDatePicker.getDate() != null)
                throw new DateException(null, "Vous devez choisir une date de fin de contrat !");


            endContractDate.setTime(Date.from(endContractDatePicker.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            employee = new Employee(userController.getLastCustomerId() + 1, userInfos.getPassword(), userInfos.getLastName(), userInfos.getFirstName(), userInfos.getSecondName(),
                    userInfos.getMaidenName(), userInfos.getBirthDate(), userInfos.getStreetName(), userInfos.getLocality(), userInfos.getEmail(),
                    userInfos.getPhone(), userInfos.getGender(), hireDate, endContractDate,
                    isEmployeeOfMonth.isSelected(), (Double)discount.getValue(),  (wantsParkingSpace.isSelected() ? employeeController.getLastParkingSpaceNumber() + 1 : null), employeeController.getManager());

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }

        return employee;
    }
}
