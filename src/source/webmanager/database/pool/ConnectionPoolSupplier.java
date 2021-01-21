package webmanager.database.pool;

import webmanager.Controller;
import webmanager.properties.PropertyManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.function.Supplier;

class ConnectionPoolSupplier implements Supplier<ConnectionPoolWrapper> {

    private static final String url;
    private static final String user;
    private static final String password;
    private static final String database;

    static {
        PropertyManager manager = PropertyManager.getInstance();

        url = manager.getProperty(PropertyManager.DATABASE_URL);
        user = manager.getProperty(PropertyManager.DATABASE_USER);
        password = manager.getProperty(PropertyManager.DATABASE_PASSWORD);
        database = manager.getProperty(PropertyManager.DATABASE_CURRENT);

        try {
            DriverManager.registerDriver(Objects.requireNonNull(DriverEnum.getInstance(database)).getDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnectionFromDriver() {
        Connection connection = null;

        try {
            if (user == null || password == null)
                connection = DriverManager.getConnection(url);
            else
                connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            Controller.logger.error("SQLException: ", e);
            System.out.println(e.getMessage());
        }
        return connection;
    }

    @Override
    public ConnectionPoolWrapper get() {
        return new ConnectionPoolWrapper(getConnectionFromDriver());
    }
}