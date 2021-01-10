package webmanager.database;

import webmanager.database.abstractions.NullDatabaseObject;
import webmanager.interfaces.DatabaseObject;
import webmanager.interfaces.DatabaseOperation;

public class DatabaseController<O extends DatabaseOperation, T extends DatabaseObject> {
    private final O operation;
    private final T operator;

    private DatabaseController(O operation, T operator) {
        this.operation = operation;
        this.operator = operator;
    }

    public static void init(String initialize) {
        if (initialize != null && initialize.equals("yes")) {
            DatabaseOperation<Void, NullDatabaseObject> tmp = new InitializeDatabase();
            tmp.operate(null);
        }
    }

    public static <O extends DatabaseOperation, T extends DatabaseObject> DatabaseController
    getDatabaseAccess(O operation, T operator) {
        return new DatabaseController<>(operation, operator);
    }

    public <T> T execute() {
        return (T) operation.operate(operator);
    }
}
