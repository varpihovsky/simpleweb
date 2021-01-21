package webmanager.database.pool;

import org.apache.log4j.Logger;
import webmanager.properties.PropertyManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private static final String CONNECTION_AMOUNT = PropertyManager.getInstance()
            .getProperty(PropertyManager.DATABASE_CONNECTIONS_AMOUNT);
    private static ArrayList<ConnectionPoolWrapper> connectionPoolWrappers;

    public static synchronized Connection getConnection() {
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
                connectionPoolWrapper.get().setTaken(true);
                return connectionPoolWrapper.get().getConnection();
            }
        }
    }

    public static synchronized void giveBack(Connection connection) {
        connectionPoolWrappers.forEach((o1) -> {
            if (o1.getConnection().equals(connection))
                o1.setTaken(false);
        });
    }

    private static class ConnectionReviver extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < connectionPoolWrappers.size(); i++) {
                try {
                    if (connectionPoolWrappers.get(i).getConnection().isClosed()) {
                        connectionPoolWrappers.remove(i);
                        connectionPoolWrappers.add(new ConnectionPoolSupplier().get());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
