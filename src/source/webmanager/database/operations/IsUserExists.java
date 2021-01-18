package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsUserExists extends DatabaseOperation<Boolean, User> {
    @Override
    public Boolean operate(User user) {
        if (user == null)
            return false;

        try {
            PreparedStatement statement = connection.prepareStatement(Constants.IS_USER_EXISTS);
            statement.setString(1, user.getUsername());
            statement.setInt(2, Integer.parseInt(user.getPassword()));

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Boolean bool = resultSet.getBoolean(1);

            resultSet.close();
            statement.close();
            return bool;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return false;
    }
}
