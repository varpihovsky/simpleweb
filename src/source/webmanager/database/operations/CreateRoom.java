package webmanager.database.operations;

import webmanager.database.abstractions.Room;
import webmanager.security.Checker;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateRoom implements DatabaseOperation<Void, Room> {
    @Override
    public Void operate(Statement statement, Room room) {
        if (Checker.isContainsWrong(room.getName()) || Checker.isContainsWrong(room.getDescription()))
            return null;

        try {
            statement.executeUpdate("INSERT INTO room_data(name, description, password) VALUES ('" +
                    room.getName() + "', '" + room.getDescription() + "', NULL");
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
