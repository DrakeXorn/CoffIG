package dataAccess;

import model.*;
import model.exceptions.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrderDBAccess implements OrderDataAccess {
    @Override
    public void addOrder(Order order) throws ConnectionException, AddDataException, ModifyException {
        Savepoint savepoint = null;
        Connection connection = null;

        try {
            connection = SingletonConnection.getInstance();
            String insertInstruction = "insert into `order` (order_number, date, is_to_take_away, order_picker) values (?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertInstruction);

            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("Order");

            insertStatement.setInt(1, order.getOrderNumber());
            insertStatement.setDate(2, new Date(order.getDate().getTimeInMillis()));
            insertStatement.setBoolean(3, order.isToTakeAway());
            insertStatement.setInt(4, order.getOrderPicker().getUserID());
            insertStatement.executeUpdate();

            if (order.getBeneficiary() != null) {
                String addBeneficiaryInstruction = "update `order` set beneficiary = ? where order_number = ?";
                PreparedStatement addBeneficiaryStatement = connection.prepareStatement(addBeneficiaryInstruction);

                addBeneficiaryStatement.setInt(1, order.getBeneficiary().getUserID());
                addBeneficiaryStatement.setInt(2, order.getOrderNumber());
                addBeneficiaryStatement.executeUpdate();
            }

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
                    String linkToppingToOrderingInstruction = "insert into supplement (order_number, drink_label, drink_id, drink_size, topping_id) values (?, ?, ?, ?, ?)";
                    PreparedStatement linkToppingToOrderingStatement = connection.prepareStatement(linkToppingToOrderingInstruction);

                    updateQuantityStockLocation(topping.getStockLocation().getAlley(),
                        topping.getStockLocation().getShelf(),
                        topping.getStockLocation().getNumber(),
                        drinkOrdering.getNbrPieces());

                    linkToppingToOrderingStatement.setInt(1, order.getOrderNumber());
                    linkToppingToOrderingStatement.setString(2, drinkOrdering.getDrink().getLabel());
                    linkToppingToOrderingStatement.setInt(3, drinkOrdering.getDrink().getCoffee().getCoffeeID());
                    linkToppingToOrderingStatement.setString(4, drinkOrdering.getSize());
                    linkToppingToOrderingStatement.setInt(5, topping.getToppingID());
                    linkToppingToOrderingStatement.executeUpdate();
                }
            }

            String insertFoodInstruction = "insert into food_ordering (food_id, order_number, nbr_pieces, selling_price) values (?, ?, ?, ?)";
            PreparedStatement insertFoodStatement = connection.prepareStatement(insertFoodInstruction);
            for (FoodOrdering foodOrdering : order.getFoodOrderings()) {
                updateQuantityStockLocation(foodOrdering.getFood().getStockLocation().getAlley(),
                        foodOrdering.getFood().getStockLocation().getShelf(),
                        foodOrdering.getFood().getStockLocation().getNumber(),
                        foodOrdering.getNbrPieces());
              
                insertFoodStatement.setInt(1, foodOrdering.getFood().getFoodId());
                insertFoodStatement.setInt(2, order.getOrderNumber());
                insertFoodStatement.setInt(3, foodOrdering.getNbrPieces());
                insertFoodStatement.setDouble(4, foodOrdering.getSellingPrice());
                insertFoodStatement.executeUpdate();
            }

            connection.commit();
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            try {
                if (connection != null)
                    connection.rollback(savepoint);
            } catch (SQLException exception2) {
                throw new AddDataException(exception2.getMessage(), "commande");
            }
            throw new AddDataException(exception.getMessage(), "commande");
        }
    }

    @Override
    public Integer getLastOrderNumber() throws ConnectionException, AllDataException {
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
            throw new AllDataException(exception.getMessage(), "commande");
        }
        return nbrOrders;
    }

    public ArrayList<Order> searchOrders(Integer customerId, GregorianCalendar startDate, GregorianCalendar endDate, Boolean isToTakeAway, Boolean isOnSite)
            throws AllDataException, ConnectionException, DoubleInputException, StringInputException, IntegerInputException {
        ArrayList<Order> orders = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();

            String sqlOrder = "select distinct o.order_number OrderNumber, o.date, o.is_to_take_away, " +
                    "do.order_number DrinkOrderNumber, do.drink_id DrinkIdDO, do.drink_label DrinkLabel, do.size, do.nbr_drinks, do.selling_price DrinkPrice," +
                    "d.coffee_id, d.label, d.is_cold, " +
                    "fo.order_number FoodOrderNumber, fo.nbr_pieces, fo.selling_price FoodPrice, " +
                    "f.food_id, f.label FoodLabel from `order` o" +
                    " left outer join drink_ordering do on o.order_number = do.order_number" +
                    " left outer join drink d on (d.coffee_id = do.drink_id and d.label = do.drink_label)" +
                    " left outer join food_ordering fo on o.order_number = fo.order_number" +
                    " left outer join food f on f.food_id = fo.food_id" +
                    " where o.beneficiary = ?" +
                    " and o.`date` between ? and ?";

            if(isToTakeAway && !isOnSite)
                sqlOrder += " and o.is_to_take_away = true";
            if(!isToTakeAway && isOnSite)
                sqlOrder += " and o.is_to_take_away = false";

            PreparedStatement orderStatement = connection.prepareStatement(sqlOrder);
            endDate.add(GregorianCalendar.HOUR, 23);
            endDate.add(GregorianCalendar.MINUTE, 59);
            orderStatement.setInt(1, customerId);
            orderStatement.setDate(2, new java.sql.Date(startDate.getTime().getTime()));
            orderStatement.setDate(3, new java.sql.Date(endDate.getTime().getTime()));

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

                drinkLabel = datasOrder.getString("DrinkLabel");
                drinkId = datasOrder.getInt("DrinkIdDO");
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
            throw new AllDataException("des commandes", exception.getMessage());
        }
        return orders;
    }

    public void updateLoyaltyCardPoints(String cardId, int numberPoints) throws ModifyException, ConnectionException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String sql = "update loyalty_card set points_number = ? where loyalty_card_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, numberPoints);
            statement.setString(2, cardId);
            statement.executeUpdate();
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ModifyException("/des points de la carte de fidélité'", exception.getMessage());
        }
    }

   public void removeRight(String loyaltyCardId, Integer advantageId) throws ConnectionException, ModifyException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String sql = "delete from `right` where loyalty_card_id = ? and advantage_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loyaltyCardId);
            statement.setInt(2, advantageId);
            statement.executeUpdate();
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ModifyException(exception.getMessage(), "droit(s)");
        }
   }

    private void updateQuantityStockLocation(Integer alley, Integer shelf, Integer number, Integer removeQuantity) throws ConnectionException, ModifyException {
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
            throw new ModifyException("stock", exception.getMessage());
        }
    }

    @Override
    public ArrayList<Drink> getAllDrinks() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        ArrayList<Drink> drinks = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String drinkSqlInstruction = "select * from drink";
            PreparedStatement drinkStatement = connection.prepareStatement(drinkSqlInstruction);
            ResultSet drinkData = drinkStatement.executeQuery();

            while (drinkData.next()) {
                String coffeeSqlInstruction = "select c.*, buying_price, quantity, expiration_date" +
                        " from coffee c join drink on c.coffee_id = drink.coffee_id" +
                        " join stock_location sl on c.stock_location_alley = sl.alley" +
                        " and c.stock_location_shelf = sl.shelf" +
                        " and c.stock_location_number = sl.number" +
                        " where drink.label = ?";
                PreparedStatement coffeeStatement = connection.prepareStatement(coffeeSqlInstruction);
                coffeeStatement.setString(1, drinkData.getString("label"));
                ResultSet coffeeData = coffeeStatement.executeQuery();

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

    @Override
    public ArrayList<Topping> getAllAvailableToppings() throws ConnectionException, AllDataException, DateException, IntegerInputException, DoubleInputException {
        ArrayList<Topping> toppings = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "select * from topping t" +
                    " join stock_location sl on t.stock_location_alley = sl.alley" +
                    " and t.stock_location_shelf = sl.shelf" +
                    " and t.stock_location_number = sl.number" +
                    " where sl.expiration_date >= ?" +
                    " and sl.quantity > 0" +
                    " order by t.topping_id";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDate(1, new Date(GregorianCalendar.getInstance().getTimeInMillis()));
            ResultSet data = preparedStatement.executeQuery();

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

    public void closeConnexion() throws ClosedConnexion, ConnectionException {
        try {
            SingletonConnection.getInstance().close();
        } catch (SQLException exception) {
            throw new ClosedConnexion(exception.getMessage());
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
    }
}
