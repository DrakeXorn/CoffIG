package dataAccess;

import model.StockLocation;
import model.Topping;
import model.exceptions.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ToppingDBAccess implements ToppingDataAccess {
    @Override
    public ArrayList<Topping> getAllAvailableToppings() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        ArrayList<Topping> toppings = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "select * from topping t join stock_location sl on t.stock_location_alley = sl.alley and t.stock_location_shelf = sl.shelf and t.stock_location_number = sl.number where sl.expiration_date >= ? and sl.quantity > 0 order by t.topping_id";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data;

            preparedStatement.setDate(1, new Date(GregorianCalendar.getInstance().getTimeInMillis()));
            data = preparedStatement.executeQuery();

            while (data.next()) {
                GregorianCalendar expirationDate = new GregorianCalendar();

                expirationDate.setTime(data.getDate("expiration_date"));

                toppings.add(new Topping(data.getInt("topping_id"),
                        data.getString("label"),
                        data.getDouble("price"),
                        new StockLocation(data.getInt("alley"),
                                data.getInt("shelf"),
                                data.getInt("number"),
                                data.getDouble("buying_price"),
                                data.getInt("quantity"),
                                expirationDate)));
            }
        } catch (SQLException exception) {
            throw new AllDataException(exception.getMessage(), "toppings");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return toppings;
    }
}
