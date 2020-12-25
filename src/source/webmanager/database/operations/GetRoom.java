package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.Room;
import webmanager.interfaces.DatabaseOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetRoom implements DatabaseOperation<Room, Room> {
    @Override
    public Room operate(Statement statement, Room room) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM room_data WHERE ROOMNAME='" + room.getName() + "'");
            Room returnRoom = new Room(resultSet.getString(1), resultSet.getString(2));
            returnRoom.setPassword(resultSet.getString(3));
            returnRoom.setAdmins(resultSet.getString(4));
            returnRoom.setPrivate(resultSet.getString(5));
            returnRoom.setLinks(resultSet.getString(6));
            return returnRoom;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }
}
