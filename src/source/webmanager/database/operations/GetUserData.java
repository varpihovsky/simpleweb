package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserData extends DatabaseOperation<User, User> {
    private User user;
    private PreparedStatement statement;

    @Override
    public User operate(User user) {
        this.user = user;

        try {
            User returnUser = getUserData(getUserTable());

            statement.close();
            return returnUser;
        } catch (SQLException e) {
            Controller.logger.severe("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }

    private ResultSet getUserTable() throws SQLException {
        statement = connection.prepareStatement(Constants.GET_USER_DATA);
        statement.setString(1, user.getUsername());

        return statement.executeQuery();
    }

    private User getUserData(ResultSet resultSet) throws SQLException {
        resultSet.next();
        User returnUser = new User.Builder()
                .withId(resultSet.getInt(1))
                .withUsername(resultSet.getString(2))
                .withEmail(resultSet.getString(3))
                .withPassword(resultSet.getString(4))
                .build();

        resultSet.close();
        return returnUser;
    }
}
