package webmanager.database.operations;

import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeUserData extends DatabaseOperation<Void, User> {
    private User user;

    @Override
    public Void operate(User user) throws SQLException {
        this.user = user;

        updateEmail();

        updatePassword();

        updateUsername();

        return null;
    }

    private void updateEmail() throws SQLException {
        if (user.getEmail() != null) {
            PreparedStatement statement = connection.prepareStatement(Constants.CHANGE_USER_EMAIL);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.executeUpdate();
            statement.close();
        }
    }

    private void updatePassword() throws SQLException {
        if (user.getPassword() != null) {
            PreparedStatement statement = connection.prepareStatement(Constants.CHANGE_USER_PASSWORD);
            statement.setInt(1, user.getPassword().hashCode());
            statement.setString(2, user.getUsername());
            statement.executeUpdate();
            statement.close();
        }
    }

    private void updateUsername() throws SQLException {
        if (user.getAdditionalData("newUsername") != null) {
            PreparedStatement statement = connection.prepareStatement(Constants.CHANGE_USER_USERNAME);
            statement.setString(1, (String) user.getAdditionalData("newUsername"));
            statement.setString(2, user.getUsername());
            statement.executeUpdate();
            statement.close();
        }
    }
}
