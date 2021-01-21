package webmanager.database.operations;

import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserIdByUsername extends DatabaseOperation<User, User> {
    @Override
    public User operate(User type) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(Constants.GET_USER_ID_BY_USERNAME);
        statement.setString(1, type.getUsername());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        User user = new User.Builder().withId(resultSet.getInt(1)).build();

        resultSet.close();
        statement.close();

        return user;
    }
}
