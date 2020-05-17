package dataAccess;

import model.Drink;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DrinkDBAccess implements DrinkDataAccess {
    @Override
    public ArrayList<Drink> getAllDrinks() throws ConnectionException, AllDataException {
        ArrayList<Drink> drinks = new ArrayList<>();

        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "select * from drink";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
        } catch (SQLException exception) {
            throw new AllDataException(exception.getMessage(), "les boissons");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return drinks;
    }
}
