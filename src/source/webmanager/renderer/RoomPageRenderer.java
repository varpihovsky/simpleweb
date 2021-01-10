package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.GetRoomData;
import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;

import javax.servlet.http.HttpServletRequest;

public class RoomPageRenderer extends Operative implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        String roomName = request.getParameter("room");
        String roomPassword = request.getParameter("roomPassword");
        Room room =
                (Room) DatabaseController.getDatabaseAccess(new GetRoomData(), new Room(roomName)).execute();
    }
}
