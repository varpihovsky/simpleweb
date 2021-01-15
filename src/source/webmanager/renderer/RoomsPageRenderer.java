package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.FindRoom;
import webmanager.file.FileManager;
import webmanager.file.abstractions.RenameOperator;
import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class RoomsPageRenderer extends Operative implements InterfaceRenderer {
    private final static int ROOMS_NUM = 30;

    @Override
    public void render(HttpServletRequest request) {
        String render = "";
        String roomName = request.getParameter("room");

        ArrayList<Room> rooms;
        Room room;

        if (!Checker.isContainsWrong(roomName)) {
            room = new Room.Builder().withName(roomName).build();
        } else {
            room = new Room.Builder().withName("").build();
        }
        room.setAdditionalData("num", ROOMS_NUM);
        rooms =
                (ArrayList<Room>) DatabaseController.getDatabaseAccess(new FindRoom(), room).execute();
        //databaseController.setOperation(DatabaseController.FIND_ROOM, room).execute();

        if (rooms != null)
            for (Room room1 : rooms)
                render += "<a href=\"" + request.getContextPath() + "#" +
                        room1.getName() + "\">\n" +
                        "                <div class=\"user\">\n" +
                        "                    <h4>" + room1.getName() + "</h4>\n" +
                        "                    <img src=\"" + fileManager.setOperation(FileManager.GET_ROOM_LOGO,
                        new RenameOperator(String.valueOf(room1.getId()))).execute() + "\" alt=\"room logo\"/>\n" +
                        "                </div>\n" +
                        "            </a>\n";


        request.setAttribute("render", render);
    }
}
