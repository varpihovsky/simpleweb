package WebManager.send;

import WebManager.SessionManager;

import javax.servlet.http.HttpServletRequest;

public class RedirectSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request) {
        String page = request.getParameter("page");
        if (page == null || page.equals(""))
            return "main";
        else if (page.equals("login") && SessionManager.checkUserSession(request.getSession()))
            return "profile";
        else {
            return page;
        }
    }
}
