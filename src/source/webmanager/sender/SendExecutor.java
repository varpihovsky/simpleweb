package webmanager.sender;

import webmanager.database.DatabaseController;
import webmanager.database.operations.required.DatabaseCommunicative;
import webmanager.file.FileManager;
import webmanager.file.operations.required.FileOperating;
import webmanager.sender.sends.InterfaceSend;
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
            return currentSend;
        }
    }

    public static String execute(HttpServletRequest request, HttpServletResponse response,
                                 FileManager fileManager, DatabaseController controller) {
        InterfaceSend send = defineSend(request);

        if (send instanceof FileOperating) {
            ((FileOperating) send).setFileManager(fileManager);
        }
        if (send instanceof DatabaseCommunicative) {
            ((DatabaseCommunicative) send).setController(controller);
        }

        return send.executeSend(request, response);
    }
}
