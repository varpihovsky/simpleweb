package webmanager.sender;

import org.apache.log4j.Logger;
import webmanager.interfaces.InterfaceSend;
import webmanager.sender.sends.RedirectSend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendExecutor {
    private static InterfaceSend defineSend(HttpServletRequest request) {
        InterfaceSend currentSend = new RedirectSend();

        String send = request.getParameter("send");
        try {
            SendEnum sendEnum = SendEnum.getInstance(send);
            currentSend = sendEnum.getCurrentSend();
            return currentSend;
        } catch (IllegalArgumentException e) {
            Logger logger = Logger.getLogger(SendExecutor.class);
            logger.error("IllegalArgumentException:\n\t" + e.getMessage() + "\n\t" + e.getLocalizedMessage() + "\n\t" +
                    e.getCause());
            return currentSend;
        }
    }

    public static String execute(HttpServletRequest request, HttpServletResponse response) {
        InterfaceSend send = defineSend(request);

        return send.executeSend(request, response);
    }
}
