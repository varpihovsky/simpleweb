package webmanager.sender.sends;

import webmanager.CookieManager;
import webmanager.SessionManager;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.GetRoomData;
import webmanager.interfaces.InterfaceSend;
import webmanager.interfaces.Operative;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class RedirectSend extends Operative implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        CookieManager manager = new CookieManager();
        String page = request.getParameter("page");

        try {
            if (!SessionManager.checkUserSession(session) &&
                    (manager.getCookiesFromRequest(request) && manager.checkUser()))
                manager.createSessionFromCookie(session);

            if (Checker.isContainsWrong(page))
                return "main";

            else if (page.equals("login") && (SessionManager.checkUserSession(session) ||
                    (manager.getCookiesFromRequest(request) && manager.checkUser())))
                return "profile";

            else if (page.equals("profile") && Checker.isContainsWrong(request.getParameter("user")) &&
                    !SessionManager.checkUserSession(session))
                return "main";

            else if (page.equals("profileSettings") && !SessionManager.checkUserSession(session))
                return "main";

            else if (page.equals("room") && (!SessionManager.checkUserSession(session) ||
                    Checker.isContainsWrong(request.getParameter("roomName"))))
                return "rooms";


            else if (page.equals("room") && request.getParameter("link") != null ||
                    request.getParameter("password") != null) {
                Room room =
                        (Room) DatabaseController.getDatabaseAccess(new GetRoomData(),
                                new Room(request.getParameter("roomName"))).execute();
                //databaseController.setOperation(DatabaseController.GET_ROOM, new Room(request.getParameter("roomName"))).execute();

                if (request.getParameter("password") != null && !room.getPassword().contains(request.getParameter("password")))
                    return "rooms";
            } else
                return page;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "main";
        }
        return "main";
    }
}
