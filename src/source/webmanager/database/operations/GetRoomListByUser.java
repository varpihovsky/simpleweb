package webmanager.database.operations;

import webmanager.database.abstractions.Room;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.DatabaseOperation;
import webmanager.security.Checker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class GetRoomListByUser implements DatabaseOperation<ArrayList<Room>, User> {
    @Override
    public ArrayList<Room> operate(Statement statement, User user) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT rooms FROM user_data WHERE USERNAME='" +
                    user.getUsername() + "'");
            resultSet.next();
            String roomString = resultSet.getString(1);

            if (!Checker.isContainsWrong(roomString)) {
                ArrayList<String> names = new ArrayList<>(Arrays.asList(roomString.split(";")));
                ArrayList<String> descriptions = new ArrayList<>();

                for (int i = 0; i < names.size(); i++) {
                    resultSet = statement.executeQuery("SELECT description FROM room_data WHERE name='"
                            + names.get(i) + "'");
                    resultSet.next();
                    descriptions.add(resultSet.getString(1));
                }

                ArrayList<Room> roomList = new ArrayList<>();
                for (int i = 0; i < names.size(); i++) {
                    roomList.add(new Room(names.get(i), descriptions.get(i)));
                }

                return roomList;
            }
            return new ArrayList<>();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
