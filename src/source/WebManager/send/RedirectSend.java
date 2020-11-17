package WebManager.send;

import javax.servlet.http.HttpServletRequest;

public class RedirectSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request) {
        if (request.getParameter("page") == null || request.getParameter("page").equals(""))
            return "/main.jsp";
        else {
            return request.getParameter("page");
        }
    }
}
