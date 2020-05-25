package dataAccess;

import model.Advantage;
import model.Customer;
import model.exceptions.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AdvantageDBAccess implements AdvantageDataAccess {
    @Override
    public ArrayList<Double> getAllAdvantageDiscounts() throws ConnectionException, AllDataException {
        ArrayList<Double> discounts = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "select DISTINCT discount from advantage order by discount";
            PreparedStatement statement = connection.prepareStatement(sqlInstruction);
            ResultSet data = statement.executeQuery();

            while (data.next()) {
                discounts.add(data.getDouble("discount"));
            }
        } catch (SQLException exception) {
            throw new AllDataException(exception.getMessage(), "avantages");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return discounts;
    }

    @Override
    public ArrayList<Advantage> searchAdvantages(Customer customer, GregorianCalendar date, Double discount, int typeAdvantage) throws AllDataException, ConnectionException, DateException, IntegerInputException, DoubleInputException {
        ArrayList<Advantage> advantages = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "select * from advantage a" +
                    " join `right` r on r.advantage_id = a.advantage_id" +
                    " join loyalty_card l on l.loyalty_card_id = r.loyalty_card_id" +
                    " where l.loyalty_card_id like ?";

            sqlInstruction += switch (typeAdvantage) {
                case 2 ->  " and a.points_required <= l.points_number and ? between a.start_date and a.end_date";
                case 3 ->  " and ((points_required > points_number) or (? not between start_date and end_date))";
                default -> "";
            };

            if (discount != null) sqlInstruction += " and a.discount = ?";

            PreparedStatement statement = connection.prepareStatement(sqlInstruction);
            statement.setString(1, customer.getLoyaltyCard().getLoyaltyCardID());

            if (typeAdvantage != 1) statement.setDate(2, new java.sql.Date(date.getTimeInMillis()));

            if (discount != null) statement.setDouble((typeAdvantage == 1 ? 2 : 3), discount);

            ResultSet data = statement.executeQuery();

            while(data.next()) {
                GregorianCalendar startDate = new GregorianCalendar();
                GregorianCalendar endDate = new GregorianCalendar();
                java.sql.Date dateSql = data.getDate("start_date");
                startDate.setTime(dateSql);
                dateSql = data.getDate("end_date");
                endDate.setTime(dateSql);

                Advantage advantage = new Advantage(
                        data.getInt("advantage_id"),
                        data.getString("label"),
                        data.getDouble("discount"),
                        startDate,
                        endDate,
                        data.getInt("points_required"));

                advantages.add(advantage);
            }
        } catch (SQLException exception) {
            throw new AllDataException(exception.getMessage(), "avantages");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return advantages;
    }
}
