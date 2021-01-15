package webmanager.sender.sends;

import webmanager.Controller;
import webmanager.CookieManager;
import webmanager.SessionManager;
import webmanager.database.abstractions.User;
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

        request.setAttribute("contextPath", request.getContextPath());

        try {
            if (Checker.isContainsWrong(page))
                return "main";

            else if (page.equals("login") && (SessionManager.checkUserSession(session) ||
                    (manager.getCookiesFromRequest(request) && manager.checkUser()))) {
                request.setAttribute("user",
                        new User.Builder()
                                .withUsername((String) session.getAttribute("username"))
                                .build());
                Controller.logger.info((String) request.getAttribute("user"));
                return "profile";
            } else if (page.equals("profile")) {
                if (request.getParameter("user") != null && !request.getParameter("user").equals(""))
                    request.setAttribute("user",
                            new User.Builder()
                                    .withUsername(request.getParameter("user"))
                                    .build());
                else request.setAttribute("user",
                        new User.Builder()
                                .withUsername((String) session.getAttribute("username"))
                                .build());
                return page;
            } else if (page.equals("profileSettings") && !SessionManager.checkUserSession(session))
                return "main";

            else if (page.equals("room") && (!SessionManager.checkUserSession(session) ||
                    Checker.isContainsWrong(request.getParameter("roomName"))))
                return "rooms";

            else
                return page;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "main";
        }
    }
}
