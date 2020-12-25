package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.interfaces.DatabaseOperation;
import webmanager.util.Checker;

import java.sql.SQLException;
import java.sql.Statement;

public class ChangeUserData implements DatabaseOperation<Void, User> {

    @Override
    public Void operate(Statement statement, User user) {
        if (Checker.isContainsWrong(user.getUsername()))
            return null;
        try {
            if (!Checker.isContainsWrong(user.getEmail())) {
                statement.executeUpdate("UPDATE user_data SET EMAIL='" + user.getEmail() +
                        "' WHERE USERNAME='" + user.getUsername() + "'");
            }
            if (!Checker.isContainsWrong(user.getPassword())) {
                statement.executeUpdate("UPDATE user_data SET PASS='" + user.getPassword() +
                        "' WHERE USERNAME='" + user.getUsername() + "'");
            }
            if (!Checker.isContainsWrong((String) user.getAdditionalData("newUsername"))) {
                statement.executeUpdate("UPDATE user_data SET USERNAME='" + user.getAdditionalData("newUsername")
                        + "' WHERE USERNAME='" + user.getUsername() + "'");
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
            return null;
        }
    }
}
