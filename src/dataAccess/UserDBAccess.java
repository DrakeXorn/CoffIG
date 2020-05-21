package dataAccess;

import model.Locality;
import model.exceptions.AllDataException;
import model.exceptions.ConnectionException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDBAccess implements UserDataAccess{
    @Override
    public ArrayList<Locality> getAllLocalities() throws AllDataException, ConnectionException {
        ArrayList<Locality> localities = new ArrayList<>();
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "select * from locality";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet datas = statement.executeQuery();

            while(datas.next())
                localities.add(new Locality(datas.getInt("postal_code"), datas.getString("city")));

        } catch (IOException exception) {
            throw new AllDataException("la récupération des localités", exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return localities;
    }

    @Override
    public int getLastCustomerId() throws AllDataException, ConnectionException {
        int nbrCustomers;
        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "select max(customer_id) from customer";
            PreparedStatement statement = connection.prepareStatement(sqlInstruction);
            ResultSet result = statement.executeQuery(sqlInstruction);

            result.next();
            nbrCustomers = result.getInt("max(customer_id)");
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new AllDataException("la récupération de l'identifiant maximal des clients", exception.getMessage());
        }

        return nbrCustomers;
    }
}
