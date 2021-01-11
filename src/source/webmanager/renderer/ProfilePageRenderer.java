package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.abstractions.User;
import webmanager.database.operations.GetRoomListByUser;
import webmanager.database.operations.GetUserIdByUsername;
import webmanager.file.FileManager;
import webmanager.file.abstractions.RenameOperator;
import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

class ProfilePageRenderer extends Operative implements InterfaceRenderer {
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
                (ArrayList<Room>) DatabaseController.getDatabaseAccess(new GetRoomListByUser(), user).execute();
//                databaseController.setOperation(DatabaseController.GET_ROOM_LIST_BY_USER, user).execute();

        for (Room room : rooms) {
            roomList += "<a href=\"#\">\n" +
                    "                <div class=\"room\">\n" +
                    "                    <h4>" + room.getName() + "</h4>" +
                    "                    <img src=\"" + fileManager.setOperation(FileManager.GET_ROOM_LOGO,
                    new RenameOperator(String.valueOf(room.getId()))).execute() +
                    "\" alt=\"room logo\"/>\n" +
                    "                    <div>\n" + room.getDescription() +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </a>";
        }


        avatar = "<img src=\"" + fileManager.setOperation(FileManager.GET_USER_AVATAR,
                new RenameOperator(String.valueOf((
                        (User) DatabaseController.getDatabaseAccess(new GetUserIdByUsername(), user)
                                .execute()).getId())))
                .execute() + "\" alt=\"avatar\"/>";

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
    }
}
