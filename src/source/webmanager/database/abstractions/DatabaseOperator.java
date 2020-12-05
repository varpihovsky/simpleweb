package webmanager.database.abstractions;

public class DatabaseOperator<T> {
    private T operator;

    public T getOperator() {
        return operator;
    }

    public DatabaseOperator(T operator) {
        this.operator = operator;
    }
}
