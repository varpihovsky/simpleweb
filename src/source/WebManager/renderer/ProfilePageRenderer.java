package WebManager.renderer;

import WebManager.security.Checker;
import WebManager.send.DataBaseFactory;
import WebManager.send.dbabstractions.Room;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfilePageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        String password = (String) request.getSession().getAttribute("password");

        String roomList = "";
        String avatar;
        String navbar;

        try {
            DataBaseFactory factory = new DataBaseFactory();
            ArrayList<Room> rooms = factory.getRoomList(username);

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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
