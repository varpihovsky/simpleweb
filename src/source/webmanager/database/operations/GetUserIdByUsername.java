package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;
import webmanager.interfaces.DatabaseOperation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserIdByUsername extends DatabaseOperation<User, User> {
    @Override
    public User operate(User type) {
        try {
            PreparedStatement statement = connection.prepareStatement(Constants.GET_USER_ID_BY_USERNAME);
            statement.setString(1, type.getUsername());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            User user = new User(resultSet.getInt(1));

            resultSet.close();
            statement.close();

            return user;
        } catch (SQLException e) {
            Controller.logger.severe(e.getMessage());
        }
        return null;
    }
}
