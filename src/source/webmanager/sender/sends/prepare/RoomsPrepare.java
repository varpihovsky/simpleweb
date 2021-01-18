package webmanager.sender.sends.prepare;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.FindRoom;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class RoomsPrepare extends Preparing {
    private final static int ROOMS_NUM = 30;

    public RoomsPrepare(String currentPage, HttpServletRequest request) {
        super(currentPage, request);
    }

    @Override
    public String prepare() {
        String roomName = request.getParameter("room");

        Room room;
        if (roomName != null)
            room = new Room.Builder().withName(roomName).build();
        else
            room = new Room.Builder().withName("").build();

        room.setAdditionalData("num", ROOMS_NUM);

        DatabaseController<FindRoom, Room> databaseController =
                new DatabaseController<>(FindRoom::new, room);

        ArrayList<Room> rooms = databaseController.execute();

        request.setAttribute("roomList", rooms);

        return currentPage;
    }
}
