package userInterface.order;

import controller.CustomerController;
import controller.OrderController;
import model.Customer;

import javax.swing.*;
import java.awt.*;

public class OrderFormTopPanel extends JPanel {
    private JLabel numberLabel, customerLabel;
    private JTextField numberField;
    private JComboBox<Customer> customersBox;

    public OrderFormTopPanel() {
        OrderController orderController = new OrderController();
        CustomerController customerController = new CustomerController();

        setLayout(new GridLayout(1, 6));

        try {
            numberLabel = new JLabel("Commande numéro : ");
            numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(numberLabel);

            numberField = new JTextField();
            numberField.setEditable(false);
            numberField.setText(String.valueOf(orderController.getLastOrderNumber() + 1));
            add(numberField);

            customerLabel = new JLabel("Client : ");
            customerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(customerLabel);

            customersBox = new JComboBox<>();
            customersBox.addItem(null);
            for (Customer customer : customerController.getAllCustomers())
                customersBox.addItem(customer);
            add(customersBox);

            add(new JLabel(""));
            add(new JLabel(""));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Integer getOrderNumber() {
        return Integer.parseInt(numberField.getText());
    }

    public Customer getBeneficiary() {
        return (Customer) customersBox.getSelectedItem();
    }
}
