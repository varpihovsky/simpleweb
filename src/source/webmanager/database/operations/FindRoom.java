package webmanager.database.operations;

import webmanager.database.DatabaseOperation;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.required.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindRoom extends DatabaseOperation<ArrayList<Room>, Room> {
    private Room room;
    private PreparedStatement statement;

    @Override
    public ArrayList<Room> operate(Room room) throws SQLException {
        this.room = room;

        ArrayList<Room> roomList;

        roomList = getRoomList(getRoomTable());

        statement.close();

        return roomList;
    }

    private ResultSet getRoomTable() throws SQLException {
        statement = connection.prepareStatement(Constants.FIND_ROOM);
        statement.setString(1, room.getName());
        statement.setInt(2, (Integer) room.getAdditionalData("num"));

        return statement.executeQuery();
    }

    private ArrayList<Room> getRoomList(ResultSet resultSet) throws SQLException {
        return getRooms(resultSet);
    }
}
