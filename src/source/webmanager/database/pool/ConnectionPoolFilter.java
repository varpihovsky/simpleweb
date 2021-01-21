package webmanager.database.pool;

import java.sql.SQLException;
import java.util.function.Predicate;

class ConnectionPoolFilter implements Predicate<ConnectionPoolWrapper> {

    @Override
    public boolean test(ConnectionPoolWrapper connectionPoolWrapper) {
        try {
            return !connectionPoolWrapper.isTaken() && !connectionPoolWrapper.getConnection().isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
