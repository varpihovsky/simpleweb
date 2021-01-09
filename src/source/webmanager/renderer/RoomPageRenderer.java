package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;

import javax.servlet.http.HttpServletRequest;

public class RoomPageRenderer extends Operative implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        String roomName = request.getParameter("room");
        String roomPassword = request.getParameter("roomPassword");
        Room room = databaseController.setOperation(DatabaseController.GET_ROOM, new Room(roomName)).execute();
    }
}
