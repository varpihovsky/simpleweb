package webmanager.database.pool;

import java.sql.Connection;

class ConnectionPoolWrapper {
    private final Connection connection;
    private boolean taken = false;

    protected ConnectionPoolWrapper(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
