package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.abstractions.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

class ProfilePageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request, DatabaseController controller) {
        String username = (String) request.getSession().getAttribute("username");

        String roomList = "";
        String avatar;

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

        request.setAttribute("avatar", avatar);
        request.setAttribute("roomlist", roomList);
        request.setAttribute("username", username);
        request.setAttribute("navbar", RendererTemplates.renderNavbar(
                (String) request.getSession().getAttribute("username"),
                (String) request.getSession().getAttribute("password")));
    }
}
