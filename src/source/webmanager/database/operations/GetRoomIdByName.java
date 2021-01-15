package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetRoomIdByName extends DatabaseOperation<Room, Room> {
    @Override
    public Room operate(Room type) {
        try {
            PreparedStatement statement = connection.prepareStatement(Constants.GET_ROOM_ID_BY_NAME);
            statement.setString(1, type.getName());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Room room = new Room.Builder().withId(resultSet.getInt(1)).build();

            resultSet.close();
            statement.close();

            return room;
        } catch (SQLException e) {
            Controller.logger.severe(e.getMessage());
        }
        return null;
    }
}
