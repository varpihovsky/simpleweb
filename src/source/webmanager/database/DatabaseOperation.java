package webmanager.database;

import org.apache.log4j.Logger;
import webmanager.database.abstractions.Room;
import webmanager.database.pool.ConnectionPool;
import webmanager.interfaces.DatabaseObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DatabaseOperation<T, U extends DatabaseObject> {
    protected Connection connection;

    public DatabaseOperation() {
        connection = ConnectionPool.getConnection();
    }

    protected ArrayList<Room> getRooms(ResultSet resultSet) throws SQLException {
        ArrayList<Room> roomList = new ArrayList<>();
        while (resultSet.next()) {
            roomList.add(new Room.Builder()
                    .withId(resultSet.getInt(1))
                    .withName(resultSet.getString(2))
                    .withDescription(resultSet.getString(3))
                    .build());
        }
        resultSet.close();
        return roomList;
    }

    private void closeConnection() {
        ConnectionPool.giveBack(connection);
    }

    T start(U type) {
        T t = null;
        try {
            t = operate(type);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(DatabaseOperation.class);
            logger.error("SQL Exception: ", e);
        }
        closeConnection();
        return t;
    }

    protected abstract T operate(U type) throws SQLException;
}
