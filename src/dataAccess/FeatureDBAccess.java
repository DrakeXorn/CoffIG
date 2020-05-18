package dataAccess;

import model.exceptions.AddDataException;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeatureDBAccess implements FeatureDataAccess {
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

    @Override
    public void addFeature(String feature) throws AddDataException, ConnectionException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInsertInstruction = "insert ignore into feature (label) values (?)";
            PreparedStatement sqlInsertStatement = connection.prepareStatement(sqlInsertInstruction);

            sqlInsertStatement.setString(1, feature);
            sqlInsertStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new AddDataException(exception.getMessage(), "caractéristique");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
    }
}
