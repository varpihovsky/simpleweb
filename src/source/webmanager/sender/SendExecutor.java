package webmanager.sender;

import webmanager.Controller;
import webmanager.database.DatabaseController;
import webmanager.file.FileManager;
import webmanager.interfaces.InterfaceSend;
import webmanager.interfaces.Operative;
import webmanager.sender.sends.RedirectSend;
import webmanager.util.Checker;

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
            Controller.logger.warning("IllegalArgumentException:\n\t" + e.getMessage() + "\n\t" + e.getLocalizedMessage() + "\n\t" +
                    e.getCause());
            return currentSend;
        }
    }

    public static String execute(HttpServletRequest request, HttpServletResponse response,
                                 FileManager fileManager, DatabaseController controller) {
        InterfaceSend send = defineSend(request);


        Checker.initializeObjects((Operative) send, fileManager, controller);

        return send.executeSend(request, response);
    }
}
