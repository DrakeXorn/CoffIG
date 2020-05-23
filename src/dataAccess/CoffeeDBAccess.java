package dataAccess;

import model.*;
import model.exceptions.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CoffeeDBAccess implements CoffeeDataAccess {
    @Override
    public ArrayList<Coffee> getAllCoffees() throws AllDataException, ConnectionException, DoubleInputException, IntegerInputException, DateException {
        ArrayList<Coffee> allCoffees = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String coffeeSqlInstruction = "select * from `coff-ig`.coffee" +
                    " join stock_location sl" +
                    " on coffee.stock_location_alley = sl.alley" +
                    " and coffee.stock_location_shelf = sl.shelf" +
                    " and coffee.stock_location_number = sl.number";
            PreparedStatement coffeeStatement = connection.prepareStatement(coffeeSqlInstruction);
            ResultSet coffeeData = coffeeStatement.executeQuery();

            while (coffeeData.next()) {
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(coffeeData.getDate("expiration_date"));

                Coffee coffee = new Coffee(coffeeData.getInt("coffee_id"),
                        coffeeData.getString("label"),
                        coffeeData.getString("origin_country"),
                        coffeeData.getInt("intensity"),
                        coffeeData.getDouble("weight_needed_for_preparation"),
                        coffeeData.getBoolean("is_in_grains"),
                        coffeeData.getBoolean("is_environment_friendly"),
                        coffeeData.getDouble("price"),
                        coffeeData.getDouble("packaging"),
                        new StockLocation(coffeeData.getInt("alley"),
                                coffeeData.getInt("shelf"),
                                coffeeData.getInt("number"),
                                coffeeData.getDouble("buying_price"),
                                coffeeData.getInt("quantity"),
                                calendar));

                int discoveryYear = coffeeData.getInt("discovery_year");
                if (!coffeeData.wasNull())
                    coffee.setDiscoveryYear(discoveryYear);

                String recommendedConsumingMoment = coffeeData.getString("recommended_consuming_moment");
                if (!coffeeData.wasNull())
                    coffee.setRecommendedConsumingMoment(recommendedConsumingMoment);

                String featuresSqlInstruction = "select label, coffee_id from feature " +
                        "join description d on feature.label = d.feature_label and d.coffee_id = ?";
                PreparedStatement featuresStatement = connection.prepareStatement(featuresSqlInstruction);
                featuresStatement.setInt(1, coffeeData.getInt("coffee_id"));
                ResultSet featureData = featuresStatement.executeQuery();
                ArrayList<String> features = new ArrayList<>();

                while (featureData.next()) {
                    features.add(featureData.getString("label"));
                }

                coffee.setFeatures(features);
                allCoffees.add(coffee);
            }
        } catch (SQLException exception) {
            throw new AllDataException(exception.getMessage(), "des cafés");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return allCoffees;
    }

    @Override
    public void addCoffee(Coffee coffee) throws ConnectionException, AddDataException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String stockSqlInstruction = "insert into stock_location (alley, shelf, number, buying_price, quantity, expiration_date) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement stockStatement = connection.prepareStatement(stockSqlInstruction);

            stockStatement.setInt(1, coffee.getStockLocation().getAlley());
            stockStatement.setInt(2, coffee.getStockLocation().getShelf());
            stockStatement.setInt(3, coffee.getStockLocation().getNumber());
            stockStatement.setDouble(4, coffee.getStockLocation().getBuyingPrice());
            stockStatement.setInt(5, coffee.getStockLocation().getQuantity());
            stockStatement.setDate(6, new Date(coffee.getStockLocation().getExpirationDate().getTimeInMillis()));
            stockStatement.executeUpdate();

            String coffeeSqlInstruction = "insert into coffee (coffee_id, label, origin_country, intensity, weight_needed_for_preparation, is_in_grains, is_environment_friendly, price, packaging, stock_location_alley, stock_location_shelf, stock_location_number) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement coffeeStatement = connection.prepareStatement(coffeeSqlInstruction);

            coffeeStatement.setInt(1, coffee.getCoffeeID());
            coffeeStatement.setString(2, coffee.getLabel());
            coffeeStatement.setString(3, coffee.getOriginCountry());
            coffeeStatement.setInt(4, coffee.getIntensity());
            coffeeStatement.setDouble(5, coffee.getWeightNeededForPreparation());
            coffeeStatement.setBoolean(6, coffee.isInGrains());
            coffeeStatement.setBoolean(7, coffee.isEnvironmentFriendly());
            coffeeStatement.setDouble(8, coffee.getPrice());
            coffeeStatement.setDouble(9, coffee.getPackaging());
            coffeeStatement.setInt(10, coffee.getStockLocation().getAlley());
            coffeeStatement.setInt(11, coffee.getStockLocation().getShelf());
            coffeeStatement.setInt(12, coffee.getStockLocation().getNumber());
            coffeeStatement.executeUpdate();

            if (coffee.getDiscoveryYear() != null) {
                String insertDiscoveryYearInstruction = "update coffee set discovery_year = ? where coffee_id = ?";
                PreparedStatement insertDiscoveryYearStatement = connection.prepareStatement(insertDiscoveryYearInstruction);

                insertDiscoveryYearStatement.setInt(1, coffee.getDiscoveryYear());
                insertDiscoveryYearStatement.setInt(2, coffee.getCoffeeID());
                insertDiscoveryYearStatement.executeUpdate();
            }

            if (coffee.getRecommendedConsumingMoment() != null) {
                String insertDiscoveryYearInstruction = "update coffee set recommended_consuming_moment = ? where coffee_id = ?";
                PreparedStatement insertDiscoveryYearStatement = connection.prepareStatement(insertDiscoveryYearInstruction);

                insertDiscoveryYearStatement.setString(1, coffee.getRecommendedConsumingMoment());
                insertDiscoveryYearStatement.setInt(2, coffee.getCoffeeID());
                insertDiscoveryYearStatement.executeUpdate();
            }

            String featureInstruction = "insert ignore into feature (label) values (?)";
            String descriptionInsertInstruction = "insert into description (feature_label, coffee_id) values (?, ?)";
            PreparedStatement featureStatement = connection.prepareStatement(featureInstruction);
            PreparedStatement descriptionStatement = connection.prepareStatement(descriptionInsertInstruction);

            descriptionStatement.setInt(2, coffee.getCoffeeID());

            for (String feature : coffee.getFeatures()) {
                featureStatement.setString(1, feature);
                featureStatement.executeUpdate();
                descriptionStatement.setString(1, feature);
                descriptionStatement.executeUpdate();
            }
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AddDataException(exception.getMessage(), "café");
        }
    }

    @Override
    public void updateCoffee(Coffee coffee) throws ConnectionException, ModifyException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String updateCoffeeInstruction = "update coffee set label = ?, origin_country = ?, intensity = ?," +
                    " weight_needed_for_preparation = ?, discovery_year = ?, is_in_grains = ?," +
                    " is_environment_friendly = ?, price = ?, packaging = ?, recommended_consuming_moment = ?" +
                    " where coffee_id = ?";
            PreparedStatement updateCoffeeStatement = connection.prepareStatement(updateCoffeeInstruction);

            updateCoffeeStatement.setString(1, coffee.getLabel());
            updateCoffeeStatement.setString(2, coffee.getOriginCountry());
            updateCoffeeStatement.setInt(3, coffee.getIntensity());
            updateCoffeeStatement.setDouble(4, coffee.getWeightNeededForPreparation());

            if (coffee.getDiscoveryYear() != null)
                updateCoffeeStatement.setInt(5, coffee.getDiscoveryYear());
            else
                updateCoffeeStatement.setNull(5, Types.INTEGER);

            updateCoffeeStatement.setBoolean(6, coffee.isInGrains());
            updateCoffeeStatement.setBoolean(7, coffee.isEnvironmentFriendly());
            updateCoffeeStatement.setDouble(8, coffee.getPrice());
            updateCoffeeStatement.setDouble(9, coffee.getPackaging());

            if (!coffee.getRecommendedConsumingMoment().isEmpty())
                updateCoffeeStatement.setString(10, coffee.getRecommendedConsumingMoment());
            else
                updateCoffeeStatement.setNull(10, Types.VARCHAR);

            updateCoffeeStatement.setInt(11, coffee.getCoffeeID());
            updateCoffeeStatement.executeUpdate();

            for (String feature : coffee.getFeatures()) {
                String addFeaturesToCoffeeInstruction = "insert ignore into feature (label) values (?)";
                String insertDescriptionInstruction = "insert ignore into description (feature_label, coffee_id) values (?, ?)";
                PreparedStatement addFeaturesStatement = connection.prepareStatement(addFeaturesToCoffeeInstruction);
                PreparedStatement insertDescriptionStatement = connection.prepareStatement(insertDescriptionInstruction);

                addFeaturesStatement.setString(1, feature);
                addFeaturesStatement.executeUpdate();

                insertDescriptionStatement.setString(1, feature);
                insertDescriptionStatement.setInt(2, coffee.getCoffeeID());
                insertDescriptionStatement.executeUpdate();
            }
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ModifyException(exception.getMessage(), "café");
        }
    }

    public int getLastCoffeeID() throws ConnectionException, AddDataException {
        int nbrCoffees;

        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "select max(coffee_id) from coffee";
            PreparedStatement statement = connection.prepareStatement(sqlInstruction);
            ResultSet result = statement.executeQuery(sqlInstruction);

            result.next();
            nbrCoffees = result.getInt("max(coffee_id)");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AddDataException(exception.getMessage(), "café");
        }
        return nbrCoffees;
    }

    @Override
    public ArrayList<String> getFeatures() throws AllDataException, ConnectionException {
        ArrayList<String> features = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String featuresSqlInstruction = "select label from feature";
            PreparedStatement featuresStatement = connection.prepareStatement(featuresSqlInstruction);
            ResultSet featureData = featuresStatement.executeQuery();

            while (featureData.next()) {
                features.add(featureData.getString("label"));
            }
        } catch (SQLException exception) {
            throw new AllDataException(exception.getMessage(), "les caractéristiques");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return features;
    }



}
