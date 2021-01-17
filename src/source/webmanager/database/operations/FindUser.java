package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindUser extends DatabaseOperation<ArrayList<User>, User> {
    private PreparedStatement statement;
    private User user;

    @Override
    public ArrayList<User> operate(User user) {
        this.user = user;

        try {
            ArrayList<User> userArr;

            userArr = getUserList(getUserTable());

            statement.close();

            return userArr;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.severe("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }

    private ResultSet getUserTable() throws SQLException {
        statement = connection.prepareStatement(Constants.FIND_USER);
        statement.setString(1, user.getUsername());
        statement.setInt(2, (Integer) user.getAdditionalData("num"));

        return statement.executeQuery();
    }

    private ArrayList<User> getUserList(ResultSet resultSet) throws SQLException {
        ArrayList<User> userArr = new ArrayList<>();
        while (resultSet.next()) {
            userArr.add(new User.Builder()
                    .withId(resultSet.getInt(1))
                    .withUsername(resultSet.getString(2))
                    .build());
        }
        resultSet.close();
        return userArr;
    }
}
