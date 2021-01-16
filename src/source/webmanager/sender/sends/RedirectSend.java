package webmanager.sender.sends;

import webmanager.interfaces.InterfaceSend;
import webmanager.interfaces.Operative;
import webmanager.sender.sends.prepare.PrepareFactory;
import webmanager.sender.sends.prepare.Preparing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectSend extends Operative implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");

        Preparing preparing = new PrepareFactory().getPrepare(page, request);
        return preparing.prepare();
    }
}
