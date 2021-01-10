package webmanager.database.pool;

import webmanager.Controller;
import webmanager.properties.PropertyManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConnectionPool {

    private static final String CONNECTION_AMOUNT = PropertyManager.getInstance()
            .getProperty(PropertyManager.DATABASE_CONNECTIONS_AMOUNT);
    private static ArrayList<ConnectionPoolWrapper> connectionPoolWrappers;

    public static synchronized Connection getConnection() {
        Controller.logger.info("Someone tried to take connection");
        if (connectionPoolWrappers == null) {
            connectionPoolWrappers = (ArrayList<ConnectionPoolWrapper>)
                    Stream.generate(new ConnectionPoolSupplier())
                            .limit(Integer.parseInt(CONNECTION_AMOUNT))
                            .collect(Collectors.toList());
        }

        new ConnectionReviver().start();

        while (true) {
            Optional<ConnectionPoolWrapper> connectionPoolWrapper =
                    connectionPoolWrappers
                            .stream()
                            .filter(new ConnectionPoolFilter())
                            .findFirst();

            if (connectionPoolWrapper.isPresent()) {
                connectionPoolWrapper.get().taken = true;
                Controller.logger.info("return connection");
                return connectionPoolWrapper.get().connection;
            }
        }
    }

    public static synchronized void giveBack(Connection connection) {
        connectionPoolWrappers.forEach((o1) -> {
            if (o1.connection.equals(connection))
                o1.taken = false;
        });
    }

    private static class ConnectionReviver extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < connectionPoolWrappers.size(); i++) {
                try {
                    if (connectionPoolWrappers.get(i).connection.isClosed()) {
                        connectionPoolWrappers.remove(i);
                        connectionPoolWrappers.add(new ConnectionPoolSupplier().get());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ConnectionPoolWrapper {
        private final Connection connection;
        private boolean taken = false;

        protected ConnectionPoolWrapper(Connection connection) {
            this.connection = connection;
        }
    }

    private static class ConnectionPoolFilter implements Predicate<ConnectionPoolWrapper> {

        @Override
        public boolean test(ConnectionPoolWrapper connectionPoolWrapper) {
            try {
                return !connectionPoolWrapper.taken && !connectionPoolWrapper.connection.isClosed();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private static class ConnectionPoolSupplier implements Supplier<ConnectionPoolWrapper> {

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

        @Override
        public ConnectionPoolWrapper get() {
            Connection connection = null;

            try {
                if (user == null || password == null)
                    connection = DriverManager.getConnection(url);
                else
                    connection = DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                Controller.logger.warning("SQLException:\n\t" + e.getMessage() +
                        "\n\t" + e.getSQLState() + "\n\t" + e.getCause());
                System.out.println(e.getMessage());
            }

            return new ConnectionPoolWrapper(connection);
        }
    }
}
