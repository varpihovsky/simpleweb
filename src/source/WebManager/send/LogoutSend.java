package WebManager.send;

import javax.servlet.http.HttpServletRequest;

public class LogoutSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request) {
        request.getSession().invalidate();
        return request.getParameter("page");
    }
}
