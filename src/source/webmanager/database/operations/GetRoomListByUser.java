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
    private PreparedStatement statement;
    private User user;

    @Override
    public ArrayList<Room> operate(User user) {
        this.user = user;

        try {
            ArrayList<Room> roomList = getRooms(getRoomTable());

            statement.close();

            return roomList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.severe("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }

    private ResultSet getRoomTable() throws SQLException {
        statement = connection.prepareStatement(Constants.GET_ROOM_LIST_BY_USER);
        statement.setString(1, user.getUsername());
        return statement.executeQuery();
    }

}
