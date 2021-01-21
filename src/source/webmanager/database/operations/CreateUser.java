package webmanager.database.operations;

import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateUser extends DatabaseOperation<Void, User> {
    private User user;

    @Override
    public Void operate(User user) throws SQLException {
        this.user = user;

        createUser();

        return null;
    }

    private void createUser() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(Constants.CREATE_USER);
        int k = 1;
        statement.setString(k++, user.getUsername());
        statement.setInt(k++, user.getPassword().hashCode());
        statement.setString(k++, user.getEmail());
        statement.executeUpdate();
        statement.close();
    }
}
