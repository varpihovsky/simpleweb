package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.DatabaseOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomAddToUser implements DatabaseOperation<Void, User> {
    @Override
    public Void operate(Statement statement, User user) {
        try {
            String currentRooms = "";
            ResultSet resultSet = statement.executeQuery("SELECT ROOMS FROM user_data WHERE USERNAME='" +
                    user.getUsername() + "'");

            resultSet.next();
            currentRooms = resultSet.getString(1);

            if (currentRooms == null) {
                statement.executeUpdate("UPDATE user_data SET \n" +
                        "ROOMS='" + user.getAdditionalData("room") + "; ' \n" +
                        "WHERE USERNAME='" + user.getUsername() + "'");
            } else
                statement.executeUpdate("UPDATE user_data SET \n" +
                        "ROOMS='" + currentRooms + "; " +
                        user.getAdditionalData("room") + "' \n" +
                        "WHERE USERNAME='" + user.getUsername() + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }
}
