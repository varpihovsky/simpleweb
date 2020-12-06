package webmanager.database.operations;

import webmanager.database.abstractions.Room;
import webmanager.database.operations.required.DatabaseOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetRoomData implements DatabaseOperation<Room, Room> {
    @Override
    public Room operate(Statement statement, Room room) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM room_data WHERE name='" + room.getName() + "'");
            return new Room(resultSet.getString(1), resultSet.getString(2));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
