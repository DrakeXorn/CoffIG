package dataAccess;

import model.*;
import model.exceptions.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CustomerDBAccess implements CustomerDataAccess {
    @Override
    public void addCustomer(Customer customer) throws AddException, ConnectionException {
        try {
            Connection connection = SingletonConnection.getInstance();

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
            throw new AddException("client", exception.getMessage());
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

            String sqlCustomer = "select * from customer c join user u on c.customer_id = u.user_id left outer join loyalty_card lc on c.loyalty_card = lc.loyalty_card_id order by user_id";
            PreparedStatement customerStatement = connection.prepareStatement(sqlCustomer);
            ResultSet datasCustomer = customerStatement.executeQuery();

            Customer customer;
            String secondName, maidenName, loyaltyCard;
            int satisfactionDegree;

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
                            registrationDateJava, datasCustomer.getInt("points_number")));
                }
                customers.add(customer);
            }
        } catch (IOException exception) {
            throw new AllDataException(exception.getMessage(), "la récupération des clients");
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return customers;
    }

    public int getLastCustomerId() throws AllDataException, ConnectionException {
        int nbrCustomers;
        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "select max(customer_id) from customer";
            PreparedStatement statement = connection.prepareStatement(sqlInstruction);
            ResultSet result = statement.executeQuery(sqlInstruction);

            result.next();
            nbrCustomers = result.getInt("max(customer_id)");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AllDataException("la récupération de l'identifiant maximal des clients", exception.getMessage());
        }

        return nbrCustomers;
    }

    public void modifyCustomer(Customer customer) throws ModifyException, ConnectionException {
        try {
            Connection connection = SingletonConnection.getInstance();
            connection.setAutoCommit(false);
            Savepoint save = connection.setSavepoint("saveBeforeUpdate");

            try {
                String sqlPhone = "select phone from user where user_id = ?";
                PreparedStatement phoneStatement = connection.prepareStatement(sqlPhone);
                phoneStatement.setInt(1, customer.getUserID());
                ResultSet dataPhone = phoneStatement.executeQuery();
                dataPhone.next();
                String oldPhone = dataPhone.getString("phone");


                String sqlUser = "update user set password = ?, last_name = ?, first_name = ?, second_name = ?, maiden_name = ?," +
                        "birth_date = ?, street_name = ?, email = ?, phone = ?, gender = ?, locality_postal_code = ?, locality_city = ? " +
                        "where user_id = ?";

                PreparedStatement userStatement = connection.prepareStatement(sqlUser);
                userStatement.setString(1, customer.getPassword());
                userStatement.setString(2, customer.getLastName());
                userStatement.setString(3, customer.getFirstName());

                if(customer.getSecondName() != null)
                    userStatement.setString(4, customer.getSecondName());
                else
                    userStatement.setNull(4, Types.VARCHAR);

                if(customer.getMaidenName() != null)
                    userStatement.setString(5, customer.getMaidenName());
                else
                    userStatement.setNull(5, Types.VARCHAR);

                userStatement.setDate(6, new java.sql.Date(customer.getBirthDate().getTimeInMillis()));
                userStatement.setString(7, customer.getStreetName());
                userStatement.setString(8, customer.getEmail());
                userStatement.setString(9, customer.getPhone());
                userStatement.setString(10, customer.getGender().toString());
                userStatement.setInt(11, customer.getLocality().getPostalCode());
                userStatement.setString(12, customer.getLocality().getCity());
                userStatement.setInt(13, customer.getUserID());
                userStatement.executeUpdate();


                String sqlCustomer = "update customer set wants_advertising = ?, satisfaction_degree = ? where customer_id = ?";
                PreparedStatement customerStatement = connection.prepareStatement(sqlCustomer);
                customerStatement.setBoolean(1, customer.getWantsAdvertising());

                if(customer.getSatisfactionDegree() != null)
                    customerStatement.setInt(2, customer.getSatisfactionDegree());
                else
                    customerStatement.setNull(2, Types.INTEGER);

                customerStatement.setInt(3, customer.getUserID());
                customerStatement.executeUpdate();


                // PROBLEME A PARTIR D ICI ***********************************************************************************


                // vérification : le client avait déjà une carte de fidélité ou non
                String sqlHasCard = "select * from loyalty_card where loyalty_card_id = ?";
                PreparedStatement hasCardStatement = connection.prepareStatement(sqlHasCard);
                hasCardStatement.setString(1, oldPhone);
                ResultSet dataHasCard = hasCardStatement.executeQuery();
                dataHasCard.next();

                String idCard = dataHasCard.getString("loyalty_card_id");
                if (dataHasCard.wasNull()) {
                    // le client n'avait pas encore de carte de fidélité

                    if (customer.getLoyaltyCard() != null) {
                        // le client veut une carte de fidélité avec le formulaire de modification

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

                } else {
                    // le client avait déjà une carte de fidélité

                    if (customer.getLoyaltyCard() != null) {
                        // le client veut toujours une carte de fidélité avec le formulaire de modification

                        if (!oldPhone.equals(customer.getPhone())) {
                            // si ne numéro de téléphone a changé, il faut mettre à jour l'identifiant de la czrte de fidélité
                            String sqlLoyaltyCard = "update loyalty_card set loyalty_card_id = ? where loyalty_card_id = ?";
                            PreparedStatement loyaltyCardStatement = connection.prepareStatement(sqlLoyaltyCard);
                            loyaltyCardStatement.setString(1, customer.getPhone());
                            loyaltyCardStatement.setString(2, oldPhone);
                            loyaltyCardStatement.executeUpdate();

                            String sqlRight = "select * from `right` where loyalty_card_id = ?";
                            PreparedStatement rightStatement = connection.prepareStatement(sqlRight);
                            rightStatement.setString(1, oldPhone);
                            ResultSet datasRight = rightStatement.executeQuery();

                            while (datasRight.next()) {
                                String sqlRightUpdate = "update `right` set loyalty_card_id = ? where loyalty_card_id = ?";
                                rightStatement = connection.prepareStatement(sqlRightUpdate);
                                rightStatement.setString(1, customer.getPhone());
                                rightStatement.setString(2, oldPhone);
                            }

                            sqlCustomer = "update customer set loyalty_card = ? where customer_id = ?";
                            customerStatement = connection.prepareStatement(sqlCustomer);
                            customerStatement.setString(1, (customer.getPhone()));
                            customerStatement.setInt(2, customer.getUserID());
                            customerStatement.executeUpdate();
                        }
                    } else {
                        // le client ne veut plus de carte de fidélité avec le formulaire de modification

                        String sqlLoyaltyCard = "delete from loyalty_card where loyalty_card_id = ?";
                        PreparedStatement loyaltyCardStatement = connection.prepareStatement(sqlLoyaltyCard);
                        loyaltyCardStatement.setString(1, oldPhone);

                        String sqlRight = "delete from `right` where loyalty_card_id = ?";
                        PreparedStatement rightStatement = connection.prepareStatement(sqlRight);
                        rightStatement.setString(1, oldPhone);

                        sqlCustomer = "update customer set loyalty_card = ? where customer_id = ?";
                        customerStatement = connection.prepareStatement(sqlCustomer);
                        customerStatement.setString(1, null);
                        customerStatement.setInt(2, customer.getUserID());
                        customerStatement.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                connection.rollback(save);
            }
        } catch (SQLException exception) {
            throw new ModifyException("client", exception.getMessage());
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
    }
}
