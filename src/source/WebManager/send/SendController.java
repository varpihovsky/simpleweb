package WebManager.send;

import javax.servlet.http.HttpServletRequest;

public class SendController {
    public InterfaceSend defineSend(HttpServletRequest request) {
        InterfaceSend currentSend = new RedirectSend();

        String send = request.getParameter("send");
        try {
            SendEnum sendEnum = SendEnum.getInstance(send);
            currentSend = sendEnum.getCurrentSend();
            return currentSend;
        } catch (IllegalArgumentException e) {
            return currentSend;
        }
    }
}
