package userInterface.userRegistration;

import controller.EmployeeController;
import controller.UserController;
import model.*;
import org.jdatepicker.JDatePicker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.GregorianCalendar;

public class EmployeeForm extends JPanel {
    private JLabel hireDateLabel, endContractDateLabel, discountLabel;
    private JDatePicker hireDate, endContractDate;
    private JCheckBox isEmployeeOfMonth, wantsParkingSpace, wantsEndContract;
    private JSpinner discount;
    private UserForm userInfos;

    public EmployeeForm(UserForm userInfos) {
        this.setLayout(new GridLayout(5, 2, 5, 5));
        this.userInfos = userInfos;

        hireDateLabel = new JLabel("Date d'embauche* :");
        hireDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(hireDateLabel);

        hireDate = new JDatePicker();
        hireDate.setShowYearButtons(true);
        hireDate.getModel().setYear(2020);
        hireDate.getModel().setMonth(0);
        hireDate.getModel().setDay(1);
        hireDate.getModel().setSelected(true);
        this.add(hireDate);

        this.add(new JLabel(""));

        wantsEndContract = new JCheckBox("Choisir une date de fin de contrat");
        wantsEndContract.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(wantsEndContract);
        wantsEndContract.addItemListener(new WantsEndContractDateListener());

        endContractDateLabel = new JLabel("Date de fin de contrat :");
        endContractDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(endContractDateLabel);

        endContractDate = new JDatePicker();
        endContractDate.setEnabled(false);
        endContractDate.setShowYearButtons(true);
        endContractDate.getModel().setYear(2020);
        endContractDate.getModel().setMonth(6);
        endContractDate.getModel().setDay(1);
        endContractDate.getModel().setSelected(false);
        this.add(endContractDate);

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
            if(event.getStateChange() == ItemEvent.SELECTED)
                endContractDate.setEnabled(true);
            else{
                endContractDate.setEnabled(false);
                endContractDate.getModel().setSelected(false);
            }
        }
    }

    public Employee createEmployee() {
        Employee employee = null;
        try {
            EmployeeController employeeController = new EmployeeController();
            UserController userController =  new UserController();

            // todo changer condition pour wantsEndContract

            employee = new Employee(userController.getLastCustomerId() + 1, userInfos.getPassword(), userInfos.getLastName(), userInfos.getFirstName(), userInfos.getSecondName(),
                    userInfos.getMaidenName(), userInfos.getBirthdate(), userInfos.getStreetName(), userInfos.getLocality(), userInfos.getEmail(),
                    userInfos.getPhone(), userInfos.getGender(), (GregorianCalendar)hireDate.getModel().getValue(),
                    (wantsEndContract.isSelected() ? (GregorianCalendar)endContractDate.getModel().getValue() : null),
                    isEmployeeOfMonth.isSelected(), (Double)discount.getValue(),  (wantsParkingSpace.isSelected() ? employeeController.getLastParkingSpaceNumber() + 1 : null), employeeController.getManager());
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
        return employee;
    }
}
