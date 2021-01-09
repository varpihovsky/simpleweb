package webmanager.sender.sends;

import webmanager.CookieManager;
import webmanager.interfaces.InterfaceSend;
import webmanager.interfaces.Operative;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutSend extends Operative implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        CookieManager manager = new CookieManager();
        request.getSession().invalidate();
        manager.getCookiesFromRequest(request);
        manager.deleteCookies(response);
        return request.getParameter("page");
    }
}
