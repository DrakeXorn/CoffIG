package dataAccess;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SingletonConnection {
    private static Connection uniqueConnexion;

    public static Connection getInstance() throws IOException, SQLException {
        if (uniqueConnexion == null) {
            String username;
            String password;
            Properties properties = new Properties();
            InputStream stream = SingletonConnection.class.getClassLoader().getResourceAsStream("dbConfig.properties");

            if (stream != null) properties.load(stream);
            else throw new FileNotFoundException("dbConfig.properties");

            username = properties.getProperty("user");
            password = properties.getProperty("password");
            uniqueConnexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/coff-ig", username, password);
        }
        return uniqueConnexion;
    }
}
