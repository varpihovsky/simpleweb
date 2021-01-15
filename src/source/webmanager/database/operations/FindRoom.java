package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindRoom extends DatabaseOperation<ArrayList<Room>, Room> {
    @Override
    public ArrayList<Room> operate(Room room) {
        try {
            PreparedStatement statement = connection.prepareStatement(Constants.FIND_ROOM);
            statement.setString(1, room.getName());
            statement.setInt(2, (Integer) room.getAdditionalData("num"));

            ResultSet resultSet = statement.executeQuery();

            ArrayList<Room> roomArr = new ArrayList<>();
            while (resultSet.next()) {
                roomArr.add(new Room.Builder()
                        .withId(resultSet.getInt(1))
                        .withName(resultSet.getString(2))
                        .withDescription(resultSet.getString(3))
                        .build());
            }

            resultSet.close();
            statement.close();
            return roomArr;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }
}
