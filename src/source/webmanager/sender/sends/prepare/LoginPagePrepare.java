package webmanager.sender.sends.prepare;

import webmanager.Controller;
import webmanager.SessionManager;
import webmanager.database.abstractions.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class LoginPagePrepare extends Preparing {
    private final HttpSession session;
    SessionManager sessionManager = new SessionManager();

    public LoginPagePrepare(String currentPage, HttpServletRequest request) {
        super(currentPage, request);
        session = request.getSession();
    }

    @Override
    public String prepare() {
        if ((sessionManager.checkUserSession(session))) {
            request.setAttribute("user",
                    new User.Builder()
                            .withUsername((String) session.getAttribute("username"))
                            .build());
            Controller.logger.info((String) request.getAttribute("user"));
            return "profile";
        }
        return currentPage;
    }
}
