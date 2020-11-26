package WebManager.renderer;

import WebManager.send.DataBaseFactory;
import WebManager.send.dbabstractions.Room;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfilePageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        String roomList = "";

        try {
            DataBaseFactory factory = new DataBaseFactory();
            ArrayList<Room> rooms = factory.getRoomList(username);

            for (Room room : rooms) {
                roomList += "<a href=\"#\">\n" +
                        "                <div class=\"room\">\n" +
                        "                    <h4>" + room.getName() + "</h4>" +
                        "                    <img src=\"roomlogos/" + room.getName() + ".jpg\" alt=\"room logo\"/>\n" +
                        "                    <div>\n" +
                        room.getDescription() +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "            </a>";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        request.setAttribute("roomlist", roomList);
        request.setAttribute("username", username);
    }
}
