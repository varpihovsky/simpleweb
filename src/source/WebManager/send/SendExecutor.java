package WebManager.send;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendExecutor {
    public static String execute(InterfaceSend send, HttpServletRequest request, HttpServletResponse response) {
        if (send.getClass() == RedirectSend.class || send.getClass() == LoginSend.class ||
                send.getClass() == LogoutSend.class)
            return send.executeSend(request, response);
        else return send.executeSend(request);
    }
}
