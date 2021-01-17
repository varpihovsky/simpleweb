package webmanager.sender.sends;

import webmanager.CookieManager;
import webmanager.interfaces.InterfaceSend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutSend implements InterfaceSend {
    CookieManager cookieManager = new CookieManager();

    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        cookieManager.getCookiesFromRequest(request);
        cookieManager.deleteCookies(response);
        return request.getParameter("page");
    }
}
