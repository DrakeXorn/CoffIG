package dataAccess;

import model.Coffee;
import model.Drink;
import model.StockLocation;
import model.exceptions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DrinkDBAccess implements DrinkDataAccess {
    @Override
    public ArrayList<Drink> getAllDrinks() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        ArrayList<Drink> drinks = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String drinkSqlInstruction = "select * from drink";
            PreparedStatement drinkStatement = connection.prepareStatement(drinkSqlInstruction);
            ResultSet drinkData = drinkStatement.executeQuery();

            while (drinkData.next()) {
                String coffeeSqlInstruction = "select c.*, buying_price, quantity, expiration_date from coffee c join drink on c.coffee_id = drink.coffee_id join stock_location sl on c.stock_location_alley = sl.alley and c.stock_location_shelf = sl.shelf and c.stock_location_number = sl.number where drink.label = ?";
                PreparedStatement coffeeStatement = connection.prepareStatement(coffeeSqlInstruction);
                ResultSet coffeeData;

                coffeeStatement.setString(1, drinkData.getString("label"));

                coffeeData = coffeeStatement.executeQuery();
                if (coffeeData.next()) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(coffeeData.getDate("expiration_date"));

                    drinks.add(new Drink(drinkData.getString("label"),
                            new Coffee(coffeeData.getInt("coffee_id"),
                                    coffeeData.getString("label"),
                                    coffeeData.getString("origin_country"),
                                    coffeeData.getInt("intensity"),
                                    coffeeData.getDouble("weight_needed_for_preparation"),
                                    coffeeData.getBoolean("is_in_grains"),
                                    coffeeData.getBoolean("is_environment_friendly"),
                                    coffeeData.getDouble("price"),
                                    coffeeData.getDouble("packaging"),
                                    new StockLocation(coffeeData.getInt("stock_location_alley"),
                                            coffeeData.getInt("stock_location_shelf"),
                                            coffeeData.getInt("stock_location_number"),
                                            coffeeData.getDouble("buying_price"),
                                            coffeeData.getInt("quantity"),
                                            calendar)),
                            drinkData.getBoolean("is_cold")));
                }
            }
        } catch (SQLException exception) {
            throw new AllDataException(exception.getMessage(), "boissons");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return drinks;
    }
}
