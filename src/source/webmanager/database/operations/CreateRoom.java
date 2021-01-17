package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateRoom extends DatabaseOperation<Void, Room> {
    private PreparedStatement statement;
    private Room room;

    @Override
    public synchronized Void operate(Room room) {
        this.room = room;

        if (this.room.isPrivate() == null)
            this.room.setPrivate("no");

        try {

            createRoom();

            assignAdminToRoom();

            assignMemberToRoom();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Controller.logger.severe("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }

    private void createRoom() throws SQLException {
        statement = connection.prepareStatement(Constants.CREATE_ROOM);

        int k = 1;
        statement.setString(k++, room.getName());
        statement.setString(k++, room.getDescription());
        statement.setInt(k++, room.getPassword().hashCode());
        statement.setString(k++, room.isPrivate());
        statement.executeUpdate();
        statement.close();
    }

    private void assignAdminToRoom() throws SQLException {
        statement = connection.prepareStatement(Constants.ASSIGN_ADMIN_TO_ROOM);
        statement.setString(1, (String) room.getAdditionalData("username"));
        statement.executeUpdate();
        statement.close();
    }

    private void assignMemberToRoom() throws SQLException {
        statement = connection.prepareStatement(Constants.ASSIGN_MEMBER_TO_ROOM);
        statement.setString(1, (String) room.getAdditionalData("username"));
        statement.executeUpdate();
        statement.close();
    }
}
