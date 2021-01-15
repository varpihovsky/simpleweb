package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.Room;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetRoomListByUser extends DatabaseOperation<ArrayList<Room>, User> {
    @Override
    public ArrayList<Room> operate(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(Constants.GET_ROOM_LIST_BY_USER);
            statement.setString(1, user.getUsername());
            ArrayList<Room> roomList = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roomList.add(new Room.Builder()
                        .withId(resultSet.getInt(1))
                        .withName(resultSet.getString(2))
                        .withDescription(resultSet.getString(3))
                        .build());
            }
            resultSet.close();
            statement.close();
            return roomList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }
}
