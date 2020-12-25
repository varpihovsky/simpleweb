package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.interfaces.DatabaseOperation;
import webmanager.util.Checker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetUserData implements DatabaseOperation<User, User> {
    @Override
    public User operate(Statement statement, User user) {
        if (Checker.isContainsWrong(user.getUsername()))
            return null;

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_data WHERE USERNAME='" +
                    user.getUsername() + "'");
            resultSet.next();
            return new User(resultSet.getString(1), resultSet.getString(3),
                    resultSet.getString(2));
        } catch (SQLException e) {
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
            return null;
        }
    }
}
