package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.interfaces.DatabaseOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FindUser implements DatabaseOperation<ArrayList<User>, User> {
    @Override
    public ArrayList<User> operate(Statement statement, User user) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT USERNAME FROM user_data ORDER BY LEVENSHTEIN('" +
                    user.getUsername() + "', USERNAME) ASC LIMIT 0, " + user.getAdditionalData("num"));

            ArrayList<User> userArr = new ArrayList<>();
            while (resultSet.next()) {
                userArr.add(new User(resultSet.getString(1)));
            }
            return userArr;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
            return null;
        }
    }
}
