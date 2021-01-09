package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;
import webmanager.interfaces.DatabaseOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomAddToUser extends DatabaseOperation<Void, User> {
    @Override
    public Void operate(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(Constants.GET_ROOMSTRING_BY_USER);
            statement.setString(1, user.getUsername());
            statement.setString(2, (String) user.getAdditionalData("room"));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        closeConnection();
        return null;
    }
}
