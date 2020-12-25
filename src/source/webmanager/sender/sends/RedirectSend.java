package webmanager.sender.sends;

import webmanager.CookieManager;
import webmanager.SessionManager;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.interfaces.InterfaceSend;
import webmanager.interfaces.Operative;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;

public class RedirectSend implements InterfaceSend, Operative {
    private DatabaseController databaseController;

    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        CookieManager manager = new CookieManager();
        String page = request.getParameter("page");

        try {
            if (!SessionManager.checkUserSession(session, databaseController) &&
                    (manager.getCookiesFromRequest(request) && manager.checkUser(databaseController)))
                manager.createSessionFromCookie(session, databaseController);

            if (Checker.isContainsWrong(page))
                return "main";

            else if (page.equals("login") && (SessionManager.checkUserSession(session, databaseController) ||
                    (manager.getCookiesFromRequest(request) && manager.checkUser(databaseController))))
                return "profile";

            else if (page.equals("profile") && Checker.isContainsWrong(request.getParameter("user")) &&
                    !SessionManager.checkUserSession(session, databaseController))
                return "main";

            else if (page.equals("profileSettings") && !SessionManager.checkUserSession(session, databaseController))
                return "main";

            else if (page.equals("room") && (!SessionManager.checkUserSession(session, databaseController) ||
                    Checker.isContainsWrong(request.getParameter("roomName"))))
                return "rooms";


            else if (page.equals("room") && request.getParameter("link") != null ||
                    request.getParameter("password") != null) {
                Room room = databaseController.setOperation(DatabaseController.GET_ROOM,
                        new Room(request.getParameter("roomName"))).execute();

                if (request.getParameter("link") != null && !room.getLinks().contains(request.getParameter("link")))
                    return "rooms";

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

    @Override
    public void set(HashMap<String, Object> bundle) {
        databaseController = (DatabaseController) bundle.get("DatabaseController");
    }
}
