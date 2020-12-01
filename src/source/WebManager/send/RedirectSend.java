package WebManager.send;

import WebManager.CookieManager;
import WebManager.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class RedirectSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request) {
        return null;
    }

    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        CookieManager manager = new CookieManager();
        String page = request.getParameter("page");

        try {
            if (!SessionManager.checkUserSession(session) && (manager.getCookiesFromRequest(request) &&
                    manager.checkUser()))
                manager.createSessionFromCookie(session);

            if (page == null || page.equals(""))
                return "main";

            else if (page.equals("login") && (SessionManager.checkUserSession(session) ||
                    (manager.getCookiesFromRequest(request) && manager.checkUser())))
                return "profile";

            else if (page.equals("profileSettings") && (!SessionManager.checkUserSession(session) ||
                    !(manager.getCookiesFromRequest(request) && manager.checkUser())))
                return "main";

            else
                return page;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "main";
        }
    }
}
