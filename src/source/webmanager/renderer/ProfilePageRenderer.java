package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.abstractions.User;
import webmanager.security.Checker;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

class ProfilePageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request, DatabaseController controller) {
        String username = request.getParameter("user");
        if (Checker.isContainsWrong(username))
            username = (String) request.getSession().getAttribute("username");

        String roomList = "";
        String avatar;
        String settings = "";

        ArrayList<Room> rooms =
                controller.setOperation(DatabaseController.GET_ROOM_LIST_BY_USER, new User(username)).execute();

        for (Room room : rooms) {
            roomList += "<a href=\"#\">\n" +
                    "                <div class=\"room\">\n" +
                    "                    <h4>" + room.getName() + "</h4>" +
                    "                    <img src=\"img/roomlogos/" + room.getName() + ".jpg\" alt=\"room logo\"/>\n" +
                    "                    <div>\n" + room.getDescription() +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </a>";
        }


        avatar = "<img src=\"img/useravatars/" + username + ".jpg\" alt=\"avatar\"/>";

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
}
