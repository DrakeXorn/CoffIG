package dataAccess;

import model.Customer;
import model.Locality;
import model.LoyaltyCard;
import model.exceptions.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CustomerDBAccess implements DataAccess{
    @Override
    public void addCustomer(Customer customer) throws AddCustomerException, ConnectionException {
        try {
            Connection connection = SingletonConnection.getInstance(); // reçoit l'adresse de la connexion

            String sqlUser = "insert into user (user_id, password, last_name, first_name, birth_date, street_name, email, phone, gender, locality_postal_code, locality_city) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement userStatement = connection.prepareStatement(sqlUser);
            userStatement.setInt(1, customer.getUserID());
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
                sqlUser = "update user set second_name = ? where user_id = ?";
                userStatement = connection.prepareStatement(sqlUser);
                userStatement.setString(1, customer.getSecondName());
                userStatement.setInt(2, customer.getUserID());
                userStatement.executeUpdate();
            }

            if(customer.getMaidenName() != null) {
                sqlUser = "update user set maiden_name = ? where user_id = ?";
                userStatement = connection.prepareStatement(sqlUser);
                userStatement.setString(1, customer.getMaidenName());
                userStatement.setInt(2, customer.getUserID());
                userStatement.executeUpdate();
            }

            String sqlCustomer = "insert into customer (customer_id, wants_advertising) values (?, ?)";
            PreparedStatement customerStatement = connection.prepareStatement(sqlCustomer);
            customerStatement.setInt(1, customer.getUserID());
            customerStatement.setBoolean(2, customer.getWantsAdvertising());
            customerStatement.executeUpdate();

            if(customer.getSatisfactionDegree() != null){
                sqlCustomer = "update customer set satisfaction_degree = ? where customer_id = ?";
                customerStatement = connection.prepareStatement(sqlCustomer);
                customerStatement.setInt(1, customer.getSatisfactionDegree());
                customerStatement.setInt(2, customer.getUserID());
                customerStatement.executeUpdate();
            }

            if(customer.getLoyaltyCard() != null){
                String sqlLoyaltyCard = "insert into loyalty_card(loyalty_card_id, registration_date, points_number) values (?, ?, ?)";
                PreparedStatement loyaltyCardStatement = connection.prepareStatement(sqlLoyaltyCard);
                loyaltyCardStatement.setString(1, customer.getLoyaltyCard().getLoyaltyCardID());
                loyaltyCardStatement.setDate(2, new java.sql.Date(customer.getLoyaltyCard().getRegistrationDate().getTimeInMillis()));
                loyaltyCardStatement.setInt(3, customer.getLoyaltyCard().getPointsNumber());
                loyaltyCardStatement.executeUpdate();

                sqlCustomer = "update customer set loyalty_card = ? where customer_id = ?";
                customerStatement = connection.prepareStatement(sqlCustomer);
                customerStatement.setString(1, customer.getLoyaltyCard().getLoyaltyCardID());
                customerStatement.setInt(2, customer.getUserID());
                customerStatement.executeUpdate();
            }
        } catch (SQLException exception) {
            throw new AddCustomerException("client", exception.getMessage());
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
    }

    @Override
    public ArrayList<Locality> getAllLocalities() throws AllDataException, ConnectionException {
        ArrayList<Locality> localities = new ArrayList<>();
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "select * from locality";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet datas = statement.executeQuery();

            while(datas.next())
                localities.add(new Locality(datas.getInt("postal_code"), datas.getString("city")));

            //connection.close();

        } catch (IOException exception) {
            throw new AllDataException("la récupération des localités", exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return localities;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws AllDataException, ConnectionException, CharacterInputException, DateException, StringInputException, IntegerInputException {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();

            String sqlCustomer = "select * from customer c join user u on c.customer_id = u.user_id left outer join loyalty_card lc on c.loyalty_card = lc.loyalty_card_id";
            PreparedStatement customerStatement = connection.prepareStatement(sqlCustomer);
            ResultSet datasCustomer = customerStatement.executeQuery();

            Customer customer = null;
            String secondName, maidenName, loyaltyCard;
            Integer satisfactionDegree;

            while(datasCustomer.next()) {
                GregorianCalendar birthDateJava = new GregorianCalendar();
                java.sql.Date birthDateSql = datasCustomer.getDate("birth_date");
                birthDateJava.setTime(birthDateSql);

                customer = new Customer(datasCustomer.getInt("customer_id"),
                        datasCustomer.getString("password"),
                        datasCustomer.getString("last_name"),
                        datasCustomer.getString("first_name"),
                        birthDateJava,
                        datasCustomer.getString("street_name"),
                        new Locality(datasCustomer.getInt("locality_postal_code"),
                                datasCustomer.getString("locality_city")),
                        datasCustomer.getString("email"),
                        datasCustomer.getString("phone"),
                        datasCustomer.getString("gender").charAt(0),
                        datasCustomer.getBoolean("wants_advertising"));

                secondName = datasCustomer.getString("second_name");
                if(!datasCustomer.wasNull())
                    customer.setSecondName(secondName);

                maidenName = datasCustomer.getString("maiden_name");
                if(!datasCustomer.wasNull())
                    customer.setMaidenName(maidenName);

                satisfactionDegree = datasCustomer.getInt("satisfaction_degree");
                if(!datasCustomer.wasNull())
                    customer.setSatisfactionDegree(satisfactionDegree);

                loyaltyCard = datasCustomer.getString("loyalty_card");
                if(!datasCustomer.wasNull()){
                    java.sql.Date registrationDateSql = datasCustomer.getDate("registration_date");
                    GregorianCalendar registrationDateJava = new GregorianCalendar();
                    registrationDateJava.setTime(registrationDateSql);

                    customer.addLoyaltyCard(new LoyaltyCard(datasCustomer.getString("loyalty_card_id"),
                            registrationDateJava, datasCustomer.getInt("points_number"), customer));
                }
                customers.add(customer);
            }
        } catch (IOException exception) {
            throw new AllDataException("la récupération des clients", exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return customers;
    }




    public void modifyCustomer(Customer customer) throws AddCustomerException, ConnectionException {
        try {
            Connection connection = SingletonConnection.getInstance(); // reçoit l'adresse de la connexion

            String sqlUser = "update user set password = ?, last_name = ?, first_name = ?, birth_date = ?, " +
                    "street_name = ?, gender = ?, locality_postal_code = ?, locality_city = ? " +
                    "where user_id = ?";
            PreparedStatement userStatement = connection.prepareStatement(sqlUser);
            userStatement.setString(1, customer.getPassword());
            userStatement.setString(2, customer.getLastName());
            userStatement.setString(3, customer.getFirstName());
            userStatement.setDate(4, new java.sql.Date(customer.getBirthDate().getTimeInMillis()));
            userStatement.setString(5, customer.getStreetName());
            userStatement.setString(6, customer.getGender().toString());
            userStatement.setInt(7, customer.getLocality().getPostalCode());
            userStatement.setString(8, customer.getLocality().getCity());
            userStatement.setInt(9, customer.getUserID());
            userStatement.executeUpdate();

            if(customer.getSecondName() != null){
                sqlUser = "update user set second_name = ? where user_id = ?";
                userStatement = connection.prepareStatement(sqlUser);
                userStatement.setString(1, customer.getSecondName());
                userStatement.setInt(2, customer.getUserID());
                userStatement.executeUpdate();
            }

            if(customer.getMaidenName() != null) {
                sqlUser = "update user set maiden_name = ? where user_id = ?";
                userStatement = connection.prepareStatement(sqlUser);
                userStatement.setString(1, customer.getMaidenName());
                userStatement.setInt(2, customer.getUserID());
                userStatement.executeUpdate();
            }

            sqlUser = "select email, phone from user where user_id = ?";
            userStatement = connection.prepareStatement(sqlUser);
            userStatement.setInt(1, customer.getUserID());
            ResultSet datasUser = userStatement.executeQuery();

            if(!customer.getEmail().equals(datasUser.getString("email"))){
                sqlUser = "update user set email = ? where user_id = ?";
                userStatement = connection.prepareStatement(sqlUser);
                userStatement.setString(1, customer.getEmail());
                userStatement.setInt(2, customer.getUserID());
                userStatement.executeUpdate();
            }

            if(!customer.getPhone().equals(datasUser.getString("phone"))){
                sqlUser = "update user set phone = ? where user_id = ?";
                userStatement = connection.prepareStatement(sqlUser);
                userStatement.setString(1, customer.getPhone());
                userStatement.setInt(2, customer.getUserID());
                userStatement.executeUpdate();
            }

            String sqlCustomer = "update customer set wants_advertising = ? where customer_id = ?";
            PreparedStatement customerStatement = connection.prepareStatement(sqlCustomer);
            customerStatement.setBoolean(1, customer.getWantsAdvertising());
            customerStatement.setInt(2, customer.getUserID());
            customerStatement.executeUpdate();

            if(customer.getSatisfactionDegree() != null){
                sqlCustomer = "update customer set satisfaction_degree = ? where customer_id = ?";
                customerStatement = connection.prepareStatement(sqlCustomer);
                customerStatement.setInt(1, customer.getSatisfactionDegree());
                customerStatement.setInt(2, customer.getUserID());
                customerStatement.executeUpdate();
            }

            if(customer.getLoyaltyCard() != null){
                String sqlLoyaltyCard = "update loyalty_card set loyalty_card_id = ? where loyalty_card_id = ?";
                PreparedStatement loyaltyCardStatement = connection.prepareStatement(sqlLoyaltyCard);
                loyaltyCardStatement.setString(1, customer.getLoyaltyCard().getLoyaltyCardID());
                loyaltyCardStatement.setString(2, datasUser.getString("phone"));
                loyaltyCardStatement.executeUpdate();

                String sqlRight = "update right set loyalty_card_id = ? where loyalty_card_id = ?";
                PreparedStatement rightStatement = connection.prepareStatement(sqlRight);
                rightStatement.setString(1, customer.getLoyaltyCard().getLoyaltyCardID());
                rightStatement.setString(2, datasUser.getString("phone"));

                sqlCustomer = "update customer set loyalty_card = ? where customer_id = ?";
                customerStatement = connection.prepareStatement(sqlCustomer);
                customerStatement.setString(1, customer.getLoyaltyCard().getLoyaltyCardID());
                customerStatement.setInt(2, customer.getUserID());
                customerStatement.executeUpdate();
            }
        } catch (SQLException exception) {
            throw new AddCustomerException("client", exception.getMessage());
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
    }



}
