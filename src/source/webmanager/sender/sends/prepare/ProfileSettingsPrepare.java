package webmanager.sender.sends.prepare;

import webmanager.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class ProfileSettingsPrepare extends Preparing {
    SessionManager sessionManager = new SessionManager();
    HttpSession session;

    public ProfileSettingsPrepare(String currentPage, HttpServletRequest request) {
        super(currentPage, request);
        session = request.getSession();
    }

    @Override
    public String prepare() {
        if (!sessionManager.checkUserSession(session))
            return "main";
        return currentPage;
    }
}
