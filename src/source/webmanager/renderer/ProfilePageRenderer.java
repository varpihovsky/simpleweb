package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.abstractions.User;
import webmanager.file.FileManager;
import webmanager.file.abstractions.RenameOperator;
import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

class ProfilePageRenderer implements InterfaceRenderer, Operative {
    private DatabaseController databaseController;
    private FileManager fileManager;

    @Override
    public void render(HttpServletRequest request) {
        String username = request.getParameter("user");
        boolean sessionProfile = true;

        if (Checker.isContainsWrong(username)) {
            username = (String) request.getSession().getAttribute("username");
            sessionProfile = false;
        }

        String roomList = "";
        String avatar;
        String settings = "";

        User user = new User(username);

        if (!sessionProfile)
            user.setAdditionalData("showPrivate", "yes");
        else
            user.setAdditionalData("showPrivate", "no");

        ArrayList<Room> rooms =
                databaseController.setOperation(DatabaseController.GET_ROOM_LIST_BY_USER, user).execute();

        for (Room room : rooms) {
            roomList += "<a href=\"#\">\n" +
                    "                <div class=\"room\">\n" +
                    "                    <h4>" + room.getName() + "</h4>" +
                    "                    <img src=\"" + fileManager.setOperation(FileManager.GET_ROOM_LOGO,
                    new RenameOperator(room.getName())).execute() +
                    "\" alt=\"room logo\"/>\n" +
                    "                    <div>\n" + room.getDescription() +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </a>";
        }


        avatar = "<img src=\"" + fileManager.setOperation(FileManager.GET_USER_AVATAR,
                new RenameOperator(username)).execute() + "\" alt=\"avatar\"/>";

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
    public void set(HashMap<String, Object> bundle) {
        databaseController = (DatabaseController) bundle.get("DatabaseController");
        fileManager = (FileManager) bundle.get("FileManager");
    }
}
