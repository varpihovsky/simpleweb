package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.file.FileManager;
import webmanager.file.abstractions.RenameOperator;
import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomsPageRenderer implements InterfaceRenderer, Operative {
    private final static int ROOMS_NUM = 30;

    private DatabaseController databaseController;
    private FileManager fileManager;

    @Override
    public void render(HttpServletRequest request) {
        String render = "";
        String roomname = request.getParameter("room");

        ArrayList<Room> rooms;
        Room room;

        if (!Checker.isContainsWrong(roomname)) {
            room = new Room(roomname);
            room.setAdditionalData("num", ROOMS_NUM);
            rooms = databaseController.setOperation(DatabaseController.FIND_ROOM,
                    room).execute();
        } else {
            room = new Room("");
            room.setAdditionalData("num", ROOMS_NUM);
            rooms = databaseController.setOperation(DatabaseController.FIND_ROOM,
                    room).execute();
        }

        if (rooms != null)
            for (Room room1 : rooms)
                render += "<a href=\"" + request.getContextPath() + "#" +
                        room1.getName() + "\">\n" +
                        "                <div class=\"user\">\n" +
                        "                    <h4>" + room1.getName() + "</h4>\n" +
                        "                    <img src=\"" + fileManager.setOperation(FileManager.GET_ROOM_LOGO,
                        new RenameOperator(room1.getName())).execute() + "\" alt=\"room logo\"/>\n" +
                        "                </div>\n" +
                        "            </a>\n";


        request.setAttribute("render", render);
        request.setAttribute("navbar", RendererTemplates.renderNavbar(
                (String) request.getSession().getAttribute("username"),
                (String) request.getSession().getAttribute("password"),
                request));
    }

    @Override
    public void set(HashMap<String, Object> bundle) {
        databaseController = (DatabaseController) bundle.get("DatabaseController");
    }
}
