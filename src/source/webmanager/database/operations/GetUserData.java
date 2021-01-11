package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;
import webmanager.interfaces.DatabaseOperation;
import webmanager.util.Checker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserData extends DatabaseOperation<User, User> {
    @Override
    public User operate(User user) {
        if (Checker.isContainsWrong(user.getUsername()))
            return null;

        try {
            PreparedStatement statement = connection.prepareStatement(Constants.GET_USER_DATA);
            statement.setString(1, user.getUsername());

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            User returnUser = new User(resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4));

            resultSet.close();
            statement.close();
            return returnUser;
        } catch (SQLException e) {
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }
}
