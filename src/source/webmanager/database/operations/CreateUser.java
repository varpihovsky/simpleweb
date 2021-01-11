package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;
import webmanager.interfaces.DatabaseOperation;
import webmanager.util.Checker;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateUser extends DatabaseOperation<Void, User> {
    @Override
    public Void operate(User user) {
        if (Checker.isContainsWrong(user.getEmail()) || Checker.isContainsWrong(user.getPassword()) ||
                Checker.isContainsWrong(user.getUsername())) {
            return null;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(Constants.CREATE_USER);
            int k = 1;
            statement.setString(k++, user.getUsername());
            statement.setInt(k++, user.getPassword().hashCode());
            statement.setString(k++, user.getEmail());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }
}
