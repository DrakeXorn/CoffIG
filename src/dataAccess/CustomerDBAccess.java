package dataAccess;

import model.Customer;
import model.exceptions.AddCustomerException;
import model.exceptions.ConnectionException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDBAccess implements CustomerDataAccess{

    @Override
    public void addCustomer(Customer customer) throws AddCustomerException, ConnectionException {
        try {
            Connection connection = SingletonConnection.getInstance();

            if(customer.getLoyaltyCard() != null){
                System.out.println("Création loyalty card dans DB");
                String sqlLoyaltyCard = "insert into loyalty_card(loyalty_card_id, registration_date, points_number) values (?, ?, ?)";
                PreparedStatement loyaltyCardStatement = connection.prepareStatement(sqlLoyaltyCard);
                loyaltyCardStatement.setString(1, customer.getLoyaltyCard().getLoyaltyCardID());
                loyaltyCardStatement.setDate(2, new java.sql.Date(customer.getLoyaltyCard().getRegistrationDate().getTimeInMillis()));
                loyaltyCardStatement.setInt(3, customer.getLoyaltyCard().getPointsNumber());
            }

            String sqlUser = "insert into user (user_id, password, last_name, first_name, birth_date, street_name, email, phone, gender, locality_postal_code, locality_city) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement userStatement = connection.prepareStatement(sqlUser);
            userStatement.setString(1, customer.getUserID());
            userStatement.setString(2, customer.getPassword());
            userStatement.setString(3, customer.getLastName());
            userStatement.setString(4, customer.getFirstName());
            userStatement.setDate(5, new java.sql.Date(customer.getBirthDate().getTimeInMillis()));
            userStatement.setString(6, customer.getStreetName());
            userStatement.setString(7, customer.getEmail());
            userStatement.setString(8, customer.getPhone());
            userStatement.setString(9, customer.getGender().toString());
            userStatement.setInt(10, customer.getLocality().getPostalCode());
            userStatement.setString(11, customer.getLocality().getCity());
            userStatement.executeUpdate();

            if(customer.getSecondName() != null){
                System.out.println("Ajout second nom dans DB : " + customer.getSecondName());
                sqlUser = "update user set second_name = ? where user_id = ?";
                userStatement = connection.prepareStatement(sqlUser);
                userStatement.setString(1, customer.getSecondName());
                userStatement.setString(2, customer.getUserID());
                userStatement.executeUpdate();
            }

            if(customer.getMaidenName() != null){
                System.out.println("Ajout maiden name dans DB : " + customer.getMaidenName());
                sqlUser = "update user set maiden_name = ? where user_id = ?";
                userStatement = connection.prepareStatement(sqlUser);
                userStatement.setString(1, customer.getMaidenName());
                userStatement.setString(2, customer.getUserID());
                userStatement.executeUpdate();
            }

            String sqlCustomer = "insert into customer (customer_id, wantsAdvertising) values (?, ?)";
            PreparedStatement customerStatement = connection.prepareStatement(sqlCustomer);
            customerStatement.setString(1, customer.getUserID());
            customerStatement.setBoolean(2, customer.getWantsAdvertising());
            customerStatement.executeUpdate();

            if(customer.getSatisfactionDegree() != null){
                System.out.println("Ajout satisfaction degree dans DB");
                sqlCustomer = "update customer set satisfactionDegree = ? where customer_id = ?";
                customerStatement = connection.prepareStatement(sqlCustomer);
                customerStatement.setInt(1, customer.getSatisfactionDegree());
                customerStatement.setString(2, customer.getUserID());
                customerStatement.executeUpdate();
            }

            if(customer.getLoyaltyCard() != null){
                System.out.println("Ajout loyalty card au customer dans DB : " + customer.getLoyaltyCard().getLoyaltyCardID());
                sqlCustomer = "update customer set loyalty_card = ? where customer_id = ?";
                customerStatement = connection.prepareStatement(sqlCustomer);
                customerStatement.setString(1, customer.getLoyaltyCard().getLoyaltyCardID());
                customerStatement.setString(2, customer.getUserID());
                customerStatement.executeUpdate();
            }
            connection.close();
        } catch (SQLException exception) {
            throw new AddCustomerException("client", exception.getMessage());
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
    }
}
