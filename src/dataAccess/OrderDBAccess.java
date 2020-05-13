package dataAccess;

import model.*;
import model.exceptions.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrderDBAccess implements OrderDataAccess {
    @Override
    public void addOrder(Order order) throws ConnectionException, AddDataException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String insertInstruction = "insert into `order` (order_number, date, is_to_take_away, beneficiary, order_picker) values (?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertInstruction);

            insertStatement.setInt(1, order.getOrderNumber());
            insertStatement.setDate(2, new Date(order.getDate().getTimeInMillis()));
            insertStatement.setBoolean(3, order.isToTakeAway());
            insertStatement.setInt(4, order.getBeneficiary().getUserID());
            insertStatement.setInt(5, order.getOrderPicker().getUserID());
            insertStatement.executeUpdate();

            String insertDrinksInstruction = "insert into drink_ordering (order_number, drink_label, drink_id, size, nbr_drinks, selling_price) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertDrinksStatement = connection.prepareStatement(insertDrinksInstruction);
            for (DrinkOrdering drinkOrdering : order.getDrinkOrderings()) {
                insertDrinksStatement.setInt(1, order.getOrderNumber());
                insertDrinksStatement.setString(2, drinkOrdering.getDrink().getLabel());
                insertDrinksStatement.setInt(3, drinkOrdering.getDrink().getCoffee().getCoffeeID());
                insertDrinksStatement.setString(4, drinkOrdering.getSize());
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

    public ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite)
            throws AllDataException, ConnectionException, DoubleInputException, StringInputException, IntegerInputException {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection connection = SingletonConnection.getInstance();

            String sqlOrder = "select o.order_number OrderNumber, o.date, o.is_to_take_away, " +
                    "do.order_number DrinkOrderNumber, do.drink_id, do.drink_label, do.size, do.nbr_drinks, do.selling_price DrinkPrice," +
                    "d.coffee_id, d.label DrinkLabel, d.is_cold, " +
                    "fo.order_number FoodOrderNumber, fo.nbr_pieces, fo.selling_price FoodPrice, " +
                    "f.food_id, f.label FoodLabel from `order` o" +
                    " left outer join drink_ordering do on o.order_number = do.order_number" +
                    " left outer join drink d on (d.coffee_id = do.drink_id and d.label = do.drink_label)" +
                    " left outer join food_ordering fo on o.order_number = fo.order_number" +
                    " left outer join food f on f.food_id = fo.food_id" +
                    " where o.beneficiary = ?" +
                    " and o.date between ? and ?";

            if(isToTakeAway && !isOnSite)
                sqlOrder += " and o.is_to_take_away = true";
            if(!isToTakeAway && isOnSite)
                sqlOrder += " and o.is_to_take_away = false";

            PreparedStatement orderStatement = connection.prepareStatement(sqlOrder);
            orderStatement.setInt(1, customerId);
            orderStatement.setDate(2, new java.sql.Date(startDate.getTimeInMillis()));
            orderStatement.setDate(3, new java.sql.Date(endDate.getTimeInMillis()));

            ResultSet datasOrder = orderStatement.executeQuery();

            Order order = null;
            DrinkOrdering drinkOrdering;
            int drinkId;
            FoodOrdering foodOrdering;
            int foodId;
            int previousOrderNumber = 0;
            int currentOrderNumber;

            while(datasOrder.next()) {
                currentOrderNumber = datasOrder.getInt("OrderNumber");

                if(previousOrderNumber != currentOrderNumber) {
                    GregorianCalendar dateJava = new GregorianCalendar();
                    java.sql.Date dateSql = datasOrder.getDate("date");
                    dateJava.setTime(dateSql);

                    order = new Order(currentOrderNumber,
                            dateJava,
                            datasOrder.getBoolean("is_to_take_away"));

                    orders.add(order);
                    previousOrderNumber = datasOrder.getInt("OrderNumber");
                }

                drinkId = datasOrder.getInt("DrinkOrderNumber");
                if(!datasOrder.wasNull()){
                    drinkOrdering = new DrinkOrdering(
                            new Drink(datasOrder.getString("DrinkLabel"), datasOrder.getBoolean("is_cold")),
                            datasOrder.getString("size"),
                            datasOrder.getInt("nbr_drinks"),
                            datasOrder.getDouble("DrinkPrice"));

                    if(!order.getDrinkOrderings().contains(drinkOrdering)){
                        order.addDrinkOrdering(drinkOrdering);
                        order.setPrice(datasOrder.getDouble("DrinkPrice"));
                    }
                }

                foodId = datasOrder.getInt("FoodOrderNumber");
                if(!datasOrder.wasNull()){
                    foodOrdering = new FoodOrdering(
                            new Food(datasOrder.getInt("food_id"), datasOrder.getString("FoodLabel")),
                            datasOrder.getInt("nbr_pieces"),
                            datasOrder.getDouble("FoodPrice"));

                    if(!order.getFoodOrderings().contains(foodOrdering)){
                        order.addFoodOrdering(foodOrdering);
                        order.setPrice(datasOrder.getDouble("FoodPrice"));
                    }
                }
            }
        } catch (IOException exception) {
            throw new AllDataException("la récupération des commandes", exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return orders;
    }
}
