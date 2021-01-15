package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;
import webmanager.util.Checker;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeUserData extends DatabaseOperation<Void, User> {

    @Override
    public Void operate(User user) {
        if (Checker.isContainsWrong(user.getUsername())) {
            closeConnection();
            return null;
        }

        try {
            if (!Checker.isContainsWrong(user.getEmail())) {
                PreparedStatement statement = connection.prepareStatement(Constants.CHANGE_USER_EMAIL);
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getUsername());
                statement.executeUpdate();
                statement.close();
            }
            if (!Checker.isContainsWrong(user.getPassword())) {
                PreparedStatement statement = connection.prepareStatement(Constants.CHANGE_USER_PASSWORD);
                statement.setInt(1, user.getPassword().hashCode());
                statement.setString(2, user.getUsername());
                statement.executeUpdate();
                statement.close();
            }
            if (!Checker.isContainsWrong((String) user.getAdditionalData("newUsername"))) {
                PreparedStatement statement = connection.prepareStatement(Constants.CHANGE_USER_USERNAME);
                statement.setString(1, (String) user.getAdditionalData("newUsername"));
                statement.setString(2, user.getUsername());
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }
}
