package userInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import dataAccess.*;
import javax.swing.*;

public class MainTest {
    public static void main(String[] args) {
        MainWindow menu = new MainWindow();
        /*try {
            Connection connection = SingletonConnection.getInstance();

            PreparedStatement statement = connection.prepareStatement("select * from advantage;");
            System.out.println(statement.execute());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }*/
    }
}
