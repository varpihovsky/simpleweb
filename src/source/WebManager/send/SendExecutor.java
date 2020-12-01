package WebManager.send;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendExecutor {
    public String execute(InterfaceSend send, HttpServletRequest request, HttpServletResponse response,
                          ServletContext context) {
        if (send.getClass() == RedirectSend.class || send.getClass() == LoginSend.class ||
                send.getClass() == LogoutSend.class)
            return send.executeSend(request, response);
        else if (send.getClass() == ChangeSend.class) {
            Changer changer = (Changer) send;
            changer.setServletContext(context);
            InterfaceSend sender = (InterfaceSend) changer;
            return sender.executeSend(request, response);
        } else return send.executeSend(request);
    }
}
