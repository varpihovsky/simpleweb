package webmanager.database.operations.required;

import java.sql.Statement;

public interface DatabaseOperation<T, U> {
    T operate(Statement statement, U type);
}
