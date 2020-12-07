package webmanager.database.abstractions;

public class DatabaseOperator<T> {
    private final T operator;

    public DatabaseOperator(T operator) {
        this.operator = operator;
    }

    public T getOperator() {
        return operator;
    }
}
