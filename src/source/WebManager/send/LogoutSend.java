package WebManager.send;

import WebManager.CookieManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request) {
        return null;
    }

    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        CookieManager manager = new CookieManager();
        request.getSession().invalidate();
        manager.getCookiesFromRequest(request);
        manager.deleteCookies(response);
        return request.getParameter("page");
    }
}
