package dataAccess;

import model.Food;
import model.StockLocation;
import model.exceptions.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FoodDBAccess implements FoodDataAccess {
    @Override
    public ArrayList<Food> getAllAvailableFoods() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        ArrayList<Food> availableFoods = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "select food_id, label, price, alley, shelf, number, buying_price, quantity, expiration_date" +
                    " from food" +
                    " join stock_location sl on food.stock_location_alley = sl.alley" +
                    " and food.stock_location_shelf = sl.shelf" +
                    " and food.stock_location_number = sl.number" +
                    " where expiration_date > ?" +
                    " and sl.quantity > 0" +
                    " order by food_id";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDate(1, new Date(GregorianCalendar.getInstance().getTimeInMillis()));
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                GregorianCalendar expirationDate = new GregorianCalendar();
                expirationDate.setTime(data.getDate("expiration_date"));

                availableFoods.add(new Food(data.getInt("food_id"),
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
            throw new AllDataException(exception.getMessage(), "nourritures");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return availableFoods;
    }
}
