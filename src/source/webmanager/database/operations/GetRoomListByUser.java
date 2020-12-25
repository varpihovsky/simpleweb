package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.Room;
import webmanager.database.abstractions.User;
import webmanager.interfaces.DatabaseOperation;
import webmanager.util.Checker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class GetRoomListByUser implements DatabaseOperation<ArrayList<Room>, User> {
    @Override
    public ArrayList<Room> operate(Statement statement, User user) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT ROOMS FROM user_data WHERE USERNAME='" +
                    user.getUsername() + "'");
            resultSet.next();
            String roomString = resultSet.getString(1);

            if (!Checker.isContainsWrong(roomString)) {
                ArrayList<String> names = new ArrayList<>(Arrays.asList(roomString.split("; ")));
                ArrayList<String> descriptions = new ArrayList<>();

                for (int i = 0; i < names.size(); i++) {
                    if (user.getAdditionalData("showPrivate").equals("yes"))
                        resultSet = statement.executeQuery("SELECT DESCRIPTION FROM room_data WHERE ROOMNAME='"
                                + names.get(i) + "'");
                    else
                        resultSet = statement.executeQuery("SELECT DESCRIPTION FROM room_data WHERE ROOMNAME='"
                                + names.get(i) + "'AND ISPRIVATE='no'");

                    try {
                        resultSet.next();
                        descriptions.add(resultSet.getString(1));
                    } catch (SQLException e) {
                        descriptions.add(null);
                    }
                }

                ArrayList<Room> roomList = new ArrayList<>();
                for (int i = 0; i < names.size(); i++) {
                    if (descriptions.get(i) != null)
                        roomList.add(new Room(names.get(i), descriptions.get(i)));
                }

                return roomList;
            }
            return new ArrayList<>();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
            return null;
        }
    }
}
