package webmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private final static String url = "jdbc:mariadb://localhost/echat?useUnicode=true&serverTimezone=UTC";
    private Statement statement;
    private Connection connection;

    public DatabaseConnector(String url, String user, String password, String database) {
        try {
            if (database != null && database.equals("MariaDB")) {
                DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
            }
            if (database != null && database.equals("MySQL")) {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            }
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
