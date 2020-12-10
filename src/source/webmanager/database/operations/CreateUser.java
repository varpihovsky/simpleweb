package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.DatabaseOperation;
import webmanager.security.Checker;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateUser implements DatabaseOperation<Void, User> {
    @Override
    public Void operate(Statement statement, User user) {
        if (Checker.isContainsWrong(user.getEmail()) || Checker.isContainsWrong(user.getPassword()) ||
                Checker.isContainsWrong(user.getUsername()))
            return null;

        try {
            statement.executeUpdate("INSERT INTO user_data(USERNAME, PASS, EMAIL) VALUES ('" + user.getUsername() +
                    "', '" + user.getPassword() + "', '" + user.getEmail() + "');");
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
            return null;
        }
    }
}
