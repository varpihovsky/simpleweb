package webmanager.send;

import webmanager.CookieManager;
import webmanager.database.DatabaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class LogoutSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response, DatabaseController controller) {
        CookieManager manager = new CookieManager();
        request.getSession().invalidate();
        manager.getCookiesFromRequest(request);
        manager.deleteCookies(response);
        return request.getParameter("page");
    }
}
