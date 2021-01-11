package webmanager.interfaces;

import webmanager.database.pool.ConnectionPool;

import java.sql.Connection;

public abstract class DatabaseOperation<T, U extends DatabaseObject> {
    protected Connection connection;

    public DatabaseOperation() {
        connection = ConnectionPool.getConnection();
    }

    protected void closeConnection() {
        ConnectionPool.giveBack(connection);
    }

    public T start(U type) {
        T t = operate(type);
        closeConnection();
        return t;
    }

    protected abstract T operate(U type);
}
