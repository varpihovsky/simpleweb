package WebManager.send;

import javax.servlet.http.HttpServletRequest;

public class SendController {
    public InterfaceSend defineSend(HttpServletRequest request) {
        InterfaceSend currentSend = new RedirectSend();

        String send = request.getParameter("send");
        if (send == null || send.equals("")) {
            return currentSend;
        }
        try {
            SendEnum sendEnum = SendEnum.valueOf(send.toUpperCase());
            currentSend = sendEnum.getCurrentSend();
            return currentSend;
        } catch (IllegalArgumentException e) {
            return currentSend;
        }
    }
}
