package webmanager.sender.sends;

import webmanager.CookieManager;
import webmanager.SessionManager;
import webmanager.database.DatabaseController;
import webmanager.security.Checker;
import webmanager.database.operations.required.DatabaseCommunicative;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class RedirectSend implements InterfaceSend, DatabaseCommunicative {
    private DatabaseController controller;

    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        CookieManager manager = new CookieManager();
        String page = request.getParameter("page");

        try {
            if (!SessionManager.checkUserSession(session, controller) &&
                    (manager.getCookiesFromRequest(request) && manager.checkUser(controller)))
                manager.createSessionFromCookie(session, controller);

            if (Checker.isContainsWrong(page))
                return "main";

            else if (page.equals("login") && (SessionManager.checkUserSession(session, controller) ||
                    (manager.getCookiesFromRequest(request) && manager.checkUser(controller))))
                return "profile";

            else if (page.equals("profile") && Checker.isContainsWrong(request.getParameter("user")) &&
                    !SessionManager.checkUserSession(session, controller))
                return "main";

            else if (page.equals("profileSettings") && !SessionManager.checkUserSession(session, controller))
                return "main";

            else
                return page;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "main";
        }
    }

    @Override
    public void setController(DatabaseController controller) {
        this.controller = controller;
    }
}
