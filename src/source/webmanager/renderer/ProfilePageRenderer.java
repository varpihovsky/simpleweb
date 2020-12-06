package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.abstractions.User;
import webmanager.database.operations.required.DatabaseCommunicative;
import webmanager.file.FileManager;
import webmanager.file.abstractions.FileOperator;
import webmanager.file.operations.required.FileOperating;
import webmanager.security.Checker;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

class ProfilePageRenderer implements InterfaceRenderer, DatabaseCommunicative, FileOperating {
    private DatabaseController databaseController;
    private FileManager fileManager;

    @Override
    public void render(HttpServletRequest request) {
        String username = request.getParameter("user");
        if (Checker.isContainsWrong(username))
            username = (String) request.getSession().getAttribute("username");

        String roomList = "";
        String avatar;
        String settings = "";

        ArrayList<Room> rooms =
                databaseController.setOperation(DatabaseController.GET_ROOM_LIST_BY_USER, new User(username)).execute();

        for (Room room : rooms) {
            roomList += "<a href=\"#\">\n" +
                    "                <div class=\"room\">\n" +
                    "                    <h4>" + room.getName() + "</h4>" +
                    "                    <img src=\"" + fileManager.setOperation(FileManager.GET_ROOM_LOGO,
                    new FileOperator(room.getName())).execute() +
                    "\" alt=\"room logo\"/>\n" +
                    "                    <div>\n" + room.getDescription() +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </a>";
        }


        avatar = "<img src=\"" + fileManager.setOperation(FileManager.GET_USER_AVATAR,
                new FileOperator(username)).execute() + "\" alt=\"avatar\"/>";

        if (Checker.isContainsWrong(request.getParameter("user")))
            settings = "<div class=\"profile-settings\">\n" +
                    "                    <a href=\"" + request.getContextPath() +
                    "/controller?page=profileSettings&send=redirect\">\n" +
                    "                        <img src=\"img/interface/settings.svg\" alt=\"settings\"/>\n" +
                    "                    </a>\n" +
                    "                </div>\n";

        request.setAttribute("settings", settings);
        request.setAttribute("avatar", avatar);
        request.setAttribute("roomlist", roomList);
        request.setAttribute("username", username);
        request.setAttribute("navbar", RendererTemplates.renderNavbar(
                (String) request.getSession().getAttribute("username"),
                (String) request.getSession().getAttribute("password"),
                request));
    }

    @Override
    public void setController(DatabaseController controller) {
        databaseController = controller;
    }

    @Override
    public void setFileManager(FileManager manager) {
        fileManager = manager;
    }
}
