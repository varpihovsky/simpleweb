package webmanager.database.operations;

import webmanager.database.abstractions.User;
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
            return false;
        }
    }
}
