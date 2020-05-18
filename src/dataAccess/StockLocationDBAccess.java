package dataAccess;

import model.StockLocation;
import model.exceptions.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class StockLocationDBAccess implements StockLocationDataAccess {
    @Override
    public ArrayList<StockLocation> getAllStockLocations() throws ConnectionException, AllDataException, DoubleInputException, IntegerInputException, DateException {
        ArrayList<StockLocation> stockLocations = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "select * from stock_location";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                GregorianCalendar expirationDate = new GregorianCalendar();
                expirationDate.setTime(data.getDate("expiration_date"));

                stockLocations.add(new StockLocation(data.getInt("alley"),
                        data.getInt("shelf"),
                        data.getInt("number"),
                        data.getDouble("buying_price"),
                        data.getInt("quantity"),
                        expirationDate));
            }
        } catch (SQLException exception) {
            throw new AllDataException(exception.getMessage(), "les emplacements de stock");
        } catch (IOException e) {
            throw new ConnectionException(e.getMessage());
        }

        return stockLocations;
    }

    @Override
    public void updateStockLocation(StockLocation stock) throws ConnectionException, AddDataException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String updateSockInstruction = "update stock_location set buying_price = ?, quantity = ?, expiration_date = ? where alley = ? and shelf = ? and number = ?";
            PreparedStatement updateStockStatement = connection.prepareStatement(updateSockInstruction);

            updateStockStatement.setDouble(1, stock.getBuyingPrice());
            updateStockStatement.setInt(2, stock.getQuantity());
            updateStockStatement.setDate(3, new Date(stock.getExpirationDate().getTimeInMillis()));
            updateStockStatement.setInt(4, stock.getAlley());
            updateStockStatement.setInt(5, stock.getShelf());
            updateStockStatement.setInt(6, stock.getNumber());
            updateStockStatement.executeUpdate();
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AddDataException(exception.getMessage(), "café");
        }
    }
}
