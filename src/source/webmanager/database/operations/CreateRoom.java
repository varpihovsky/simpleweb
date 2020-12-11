package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.required.DatabaseOperation;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateRoom implements DatabaseOperation<Void, Room> {
    @Override
    public Void operate(Statement statement, Room room) {
        try {
            if (room.isPrivate() == null)
                room.setPrivate("no");

            statement.executeUpdate("INSERT INTO room_data(ROOMNAME, DESCRIPTION, PASSWORD, ADMINS, ISPRIVATE) " +
                    "VALUES ('" + room.getName() + "', '" + room.getDescription() + "', '" + room.getPassword().hashCode() + "', " +
                    "'" + room.getUser() + "; ', '" + room.isPrivate() + "')");
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
            return null;
        }
    }
}
