package webmanager.database.operations;

import webmanager.Controller;
import webmanager.database.abstractions.Room;
import webmanager.interfaces.DatabaseOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FindRoom implements DatabaseOperation<ArrayList<Room>, Room> {
    @Override
    public ArrayList<Room> operate(Statement statement, Room room) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT ROOMNAME, DESCRIPTION FROM room_data " +
                    "WHERE ISPRIVATE='no' ORDER BY LEVENSHTEIN('" +
                    room.getName() + "', ROOMNAME) ASC LIMIT 0," + room.getAdditionalData("num"));

            ArrayList<Room> roomArr = new ArrayList<>();
            while (resultSet.next()) {
                roomArr.add(new Room(resultSet.getString(1), resultSet.getString(2)));
            }
            return roomArr;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
            return null;
        }
    }
}
