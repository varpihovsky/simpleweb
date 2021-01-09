package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;
import webmanager.interfaces.DatabaseOperation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindUser extends DatabaseOperation<ArrayList<User>, User> {
    @Override
    public ArrayList<User> operate(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(Constants.FIND_USER);
            statement.setString(1, user.getUsername());
            statement.setInt(2, (Integer) user.getAdditionalData("num"));

            ResultSet resultSet = statement.executeQuery();

            ArrayList<User> userArr = new ArrayList<>();
            while (resultSet.next()) {
                userArr.add(new User(resultSet.getString(1)));
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return userArr;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());

            closeConnection();
            return null;
        }
    }
}
