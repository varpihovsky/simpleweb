package webmanager.database;

import webmanager.Controller;

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
                connection = DriverManager.getConnection(url, user, password);

            statement = connection.createStatement();
        } catch (SQLException e) {
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() +
                    "\n\t" + e.getSQLState() + "\n\t" + e.getCause());
            System.out.println(e.getMessage());
        }
    }

    public Statement getConnection() {
        return statement;
    }
}
