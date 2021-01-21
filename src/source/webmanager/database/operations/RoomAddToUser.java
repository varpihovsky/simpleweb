package webmanager.database.operations;

import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomAddToUser extends DatabaseOperation<Void, User> {
    @Override
    public Void operate(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(Constants.GET_ROOMSTRING_BY_USER);
        statement.setString(1, user.getUsername());
        statement.setString(2, (String) user.getAdditionalData("room"));
        statement.executeUpdate();
        statement.close();
        return null;
    }
}
