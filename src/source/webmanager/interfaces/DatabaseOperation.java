package webmanager.interfaces;

import webmanager.database.pool.ConnectionPool;

import java.sql.Connection;

public abstract class DatabaseOperation<T, U> {
    protected Connection connection;

    public DatabaseOperation() {
        connection = ConnectionPool.getConnection();
    }

    protected void closeConnection() {
        ConnectionPool.giveBack(connection);
    }

    public abstract T operate(U type);
}
