package webmanager.database;

import webmanager.database.abstractions.NullDatabaseObject;
import webmanager.interfaces.DatabaseObject;

import java.util.function.Supplier;

public class DatabaseController<O extends DatabaseOperation, T extends DatabaseObject> {
    private final O operation;
    private final T operator;

    public DatabaseController(Supplier<? extends O> supplier, T operator) {
        this.operator = operator;
        operation = supplier.get();
    }

    public static void init(String initialize) {
        if (initialize != null && initialize.equals("yes")) {
            DatabaseOperation<Void, NullDatabaseObject> tmp = new InitializeDatabase();
            tmp.start(null);
        }
    }

    public <T> T execute() {
        return (T) operation.start(operator);
    }
}
