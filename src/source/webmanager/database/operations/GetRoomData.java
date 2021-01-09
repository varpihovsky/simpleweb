package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.required.Constants;
import webmanager.interfaces.DatabaseOperation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetRoomData extends DatabaseOperation<Room, Room> {
    @Override
    public Room operate(Room room) {
        try {
            PreparedStatement statement = connection.prepareStatement(Constants.GET_ROOM_DATA);
            statement.setString(1, room.getName());

            ResultSet resultSet = statement.executeQuery();
            Room returnRoom = new Room(resultSet.getString(1), resultSet.getString(2));

            resultSet.close();
            statement.close();
            closeConnection();
            return returnRoom;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());

            closeConnection();
            return null;
        }
    }
}
