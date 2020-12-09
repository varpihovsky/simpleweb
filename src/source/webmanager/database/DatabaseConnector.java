package webmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private Statement statement;
    private Connection connection;

    public DatabaseConnector(String url, String user, String password, String database) {
        try {
            DriverManager.registerDriver(DriverEnum.getInstance(database).getDriver());

            if (user == null || password == null)
                connection = DriverManager.getConnection(url);
            else
                connection = DriverManager.getConnection(url, "echat", "5454tankman");

            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Statement getConnection() {
        return statement;
    }
}
