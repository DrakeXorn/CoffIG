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
            PreparedStatement insertDrinkStatement = connection.prepareStatement(insertDrinksInstruction);
            for (DrinkOrdering drinkOrdering : order.getDrinkOrderings()) {
                insertDrinkStatement.setInt(1, order.getOrderNumber());
                insertDrinkStatement.setString(2, drinkOrdering.getDrink().getLabel());
                insertDrinkStatement.setInt(3, drinkOrdering.getDrink().getCoffee().getCoffeeID());
                insertDrinkStatement.setString(4, drinkOrdering.getSize());
                insertDrinkStatement.setInt(5, drinkOrdering.getNbrPieces());
                insertDrinkStatement.setDouble(6, drinkOrdering.getSellingPrice());
                insertDrinkStatement.executeUpdate();

                for (Topping topping : drinkOrdering.getToppings()) {
                    String linkToppingToOrderingInstruction = "insert into supplement (order_number, drink_id, drink_label, topping_id) values (?, ?, ?, ?)";
                    String updateStockInstruction = "update stock_location set quantity = ((select quantity from stock_location where alley = ? and shelf = ? and number = ?) - ?) where alley = ? and shelf = ? and number = ?";
                    PreparedStatement linkToppingToOrderingStatement = connection.prepareStatement(linkToppingToOrderingInstruction);
                    PreparedStatement updateStockStatement = connection.prepareStatement(updateStockInstruction);

                    linkToppingToOrderingStatement.setInt(1, order.getOrderNumber());
                    linkToppingToOrderingStatement.setInt(2, drinkOrdering.getDrink().getCoffee().getCoffeeID());
                    linkToppingToOrderingStatement.setString(3, drinkOrdering.getDrink().getCoffee().getLabel());
                    linkToppingToOrderingStatement.setInt(4, topping.getToppingID());
                    linkToppingToOrderingStatement.executeUpdate();

                    updateStockStatement.setInt(1, topping.getStock().getAlley());
                    updateStockStatement.setInt(2, topping.getStock().getShelf());
                    updateStockStatement.setInt(3, topping.getStock().getNumber());
                    updateStockStatement.setInt(4, drinkOrdering.getNbrPieces());
                    updateStockStatement.setInt(5, topping.getStock().getAlley());
                    updateStockStatement.setInt(6, topping.getStock().getShelf());
                    updateStockStatement.setInt(7, topping.getStock().getNumber());
                    updateStockStatement.executeUpdate();
                }
            }

            String insertFoodInstruction = "insert into food_ordering (food_id, order_number, nbr_pieces, selling_price) values (?, ?, ?, ?)";
            PreparedStatement insertFoodStatement = connection.prepareStatement(insertFoodInstruction);
            for (FoodOrdering foodOrdering : order.getFoodOrderings()) {
                updateStockLocation(foodOrdering.getFood().getStockLocation().getAlley(),
                        foodOrdering.getFood().getStockLocation().getShelf(),
                        foodOrdering.getFood().getStockLocation().getNumber(),
                        foodOrdering.getNbrPieces());

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
            nbrOrders = result.getInt("max(order_number)");
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
                    "do.order_number DrinkOrderNumber, do.drink_id DrinkIdDO, do.drink_label DrinkLabel, do.size, do.nbr_drinks, do.selling_price DrinkPrice," +
                    "d.coffee_id, d.label, d.is_cold, " +
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
            String drinkLabel;
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

                drinkId = datasOrder.getInt("DrinkIdDO");
                drinkLabel = datasOrder.getString("DrinkLabel");
                if(!datasOrder.wasNull()){
                    drinkOrdering = new DrinkOrdering(
                            new Drink(drinkLabel, datasOrder.getBoolean("is_cold")),
                            datasOrder.getString("size"),
                            datasOrder.getInt("nbr_drinks"),
                            datasOrder.getDouble("DrinkPrice"));

                    String sqlTopping = "select distinct t.topping_id, t.label, t.price from topping t " +
                            "join supplement s on s.topping_id = t.topping_id " +
                            "join drink_ordering d on (s.drink_label = ? and s.drink_id = ?)" +
                            "where s.order_number = ?";
                    PreparedStatement toppingStatement = connection.prepareStatement(sqlTopping);
                    toppingStatement.setString(1, drinkLabel);
                    toppingStatement.setInt(2, drinkId);
                    toppingStatement.setInt(3, currentOrderNumber);
                    ResultSet datasTopping = toppingStatement.executeQuery();

                    while(datasTopping.next()){
                        drinkOrdering.addTopping(new Topping(datasTopping.getInt("topping_id"),
                                datasTopping.getString("label"),
                                datasTopping.getDouble("price")));
                    }

                    if(!order.getDrinkOrderings().contains(drinkOrdering))
                        order.addDrinkOrdering(drinkOrdering);
                }

                foodId = datasOrder.getInt("FoodOrderNumber");
                if(!datasOrder.wasNull()){
                    foodOrdering = new FoodOrdering(
                            new Food(datasOrder.getInt("food_id"), datasOrder.getString("FoodLabel")),
                            datasOrder.getInt("nbr_pieces"),
                            datasOrder.getDouble("FoodPrice"));

                    if(!order.getFoodOrderings().contains(foodOrdering))
                        order.addFoodOrdering(foodOrdering);
                }
            }
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AllDataException("la récupération des commandes", exception.getMessage());
        }
        return orders;
    }

    public String updatePointsToLoyaltyCard(String cardId, int numberPoints) throws AllDataException, ConnectionException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String sql = "update loyalty_card set points_number = points_number + ? where loyalty_card_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, numberPoints);
            statement.setString(2, cardId);
            statement.executeUpdate();
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AllDataException("la mise à jour des points de la carte de fidélité'", exception.getMessage());
        }
        return Math.abs(numberPoints) + " ont été " + (numberPoints > 0 ?  "ajouté à" : "supprimé de") + " la carte de fidélité";
    }

    public int getPointsLoyaltyCard(String cardId) throws AllDataException, ConnectionException {
        int points = 0;
        try {
            Connection connection = SingletonConnection.getInstance();
            String sql = "select points_number from loyalty_card where loyalty_card_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cardId);
            ResultSet data = statement.executeQuery();
            if(data.next())
                points = data.getInt("points_number");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AllDataException("la récupération des points de la carte de fidélité", exception.getMessage());
        }
        return points;
    }

    public ArrayList<Integer> getPointsAdvantage(String cardId) throws AllDataException, ConnectionException {
        ArrayList<Integer> points = new ArrayList<>();
        try {
            Connection connection = SingletonConnection.getInstance();
            String sql = "select points_required from advantage a join `right` r on a.advantage_id = r.advantage_id where r.loyalty_card_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cardId);
            ResultSet data = statement.executeQuery();
            while(data.next())
                points.add(data.getInt("points_number"));

        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AllDataException("la récupération des points de l'avantage", exception.getMessage());
        }
        return points;
    }

    public void updateStockLocation(Integer alley, Integer shelf, Integer number, Integer removeQuantity) throws ConnectionException, AddDataException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String sql = "update stock_location set quantity = quantity - ? " +
                    "where alley = ? and shelf  = ? and number = ?";
            PreparedStatement updateStatement = connection.prepareStatement(sql);
            updateStatement.setInt(1, removeQuantity);
            updateStatement.setInt(2, alley);
            updateStatement.setInt(3, shelf);
            updateStatement.setInt(4, number);
            updateStatement.executeUpdate();
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AddDataException("la mise à jour du stock", exception.getMessage());
        }
    }
}
