package webmanager.send;

import webmanager.CookieManager;
import webmanager.SessionManager;
import webmanager.database.DatabaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

class RedirectSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response, DatabaseController controller) {
        HttpSession session = request.getSession();
        CookieManager manager = new CookieManager();
        String page = request.getParameter("page");

        try {
            if (!SessionManager.checkUserSession(session, controller) &&
                    (manager.getCookiesFromRequest(request) && manager.checkUser(controller)))
                manager.createSessionFromCookie(session, controller);

            if (page == null || page.equals(""))
                return "main";

            else if (page.equals("login") && (SessionManager.checkUserSession(session, controller) ||
                    (manager.getCookiesFromRequest(request) && manager.checkUser(controller))))
                return "profile";

            else if (page.equals("profileSettings") && !SessionManager.checkUserSession(session, controller))
                return "main";

            else
                return page;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "main";
        }
    }
}
