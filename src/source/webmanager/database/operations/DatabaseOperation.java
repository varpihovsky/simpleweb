package webmanager.database.operations;

import java.sql.Statement;

public interface DatabaseOperation<T, U> {
    public T operate(Statement statement, U type);
}
