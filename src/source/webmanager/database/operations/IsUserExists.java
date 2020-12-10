package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.DatabaseOperation;
import webmanager.security.Checker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IsUserExists implements DatabaseOperation<Boolean, User> {
    @Override
    public Boolean operate(Statement statement, User user) {
        if (Checker.isContainsWrong(user.getUsername()) || Checker.isContainsWrong(user.getPassword()))
            return false;

        try {
            ResultSet resultSet = statement.executeQuery("SELECT EXISTS(SELECT USERNAME FROM user_data WHERE USERNAME='" +
                    user.getUsername() + "' AND PASS='" + user.getPassword() + "')");
            resultSet.next();
            Boolean bool = resultSet.getBoolean(1);
            return bool;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
            return false;
        }
    }
}
