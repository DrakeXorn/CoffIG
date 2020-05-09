package dataAccess;

import model.DrinkOrdering;
import model.FoodOrdering;
import model.Order;
import model.exceptions.AddDataException;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class OrderDBAccess implements OrderDataAccess {
    @Override
    public ArrayList<Order> getAllOrders() throws AllDataException {
        return null;
    }

    @Override
    public boolean addOrder(Order order) throws ConnectionException, AddDataException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String insertInstruction = "insert into `order` (order_number, date, is_to_take_away, beneficiary, order_picker) values (?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertInstruction);

            insertStatement.setInt(1, order.getOrderNumber());
            insertStatement.setDate(2, new Date(order.getDate().getTimeInMillis()));
            insertStatement.setBoolean(3, order.isToTakeAway());
            insertStatement.setInt(4, order.getBeneficiary().getUserId());
            insertStatement.setInt(5, order.getOrderPicker().getUserId());
            insertStatement.executeUpdate();

            String insertDrinksInstruction = "insert into drink_ordering (order_number, drink_label, drink_id, size, nbr_drinks, selling_price) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertDrinksStatement = connection.prepareStatement(insertDrinksInstruction);
            for (DrinkOrdering drinkOrdering : order.getDrinkOrderings()) {
                insertDrinksStatement.setInt(1, order.getOrderNumber());
                insertDrinksStatement.setString(2, drinkOrdering.getDrink().getLabel());
                insertDrinksStatement.setInt(3, drinkOrdering.getDrink().getCoffee().getCoffeeID());
                insertDrinksStatement.setString(4, drinkOrdering.getDrink().getSize());
                insertDrinksStatement.setInt(5, drinkOrdering.getNbrPieces());
                insertDrinksStatement.setDouble(6, drinkOrdering.getSellingPrice());
                insertDrinksStatement.executeUpdate();
            }

            String insertFoodInstruction = "insert into food_ordering (food_id, order_number, nbr_pieces, selling_price) values (?, ?, ?, ?)";
            PreparedStatement insertFoodStatement = connection.prepareStatement(insertFoodInstruction);
            for (FoodOrdering foodOrdering : order.getFoodOrderings()) {
                insertFoodStatement.setInt(1, foodOrdering.getFood().getFoodId());
                insertFoodStatement.setInt(2, order.getOrderNumber());
                insertFoodStatement.setInt(3, foodOrdering.getNbrPieces());
                insertFoodStatement.setDouble(4, foodOrdering.getSellingPrice());
                insertFoodStatement.executeUpdate();
            }
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AddDataException(exception.getMessage(), "commande");
        }

        return true;
    }

    @Override
    public boolean removeOrder(Order order) throws ConnectionException, AddDataException {
        return false;
    }

    @Override
    public boolean updateOrder(Order order) throws ConnectionException, AddDataException {
        return false;
    }

    @Override
    public Integer getLastOrderNumber() throws ConnectionException, AddDataException {
        int nbrOrders;

        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "select max(order_number) from `order`";
            PreparedStatement statement = connection.prepareStatement(sqlInstruction);
            ResultSet result = statement.executeQuery(sqlInstruction);

            result.next();
            nbrOrders = result.getInt("max(coffee_id)");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AddDataException(exception.getMessage(), "commande");
        }

        return nbrOrders;
    }
}
