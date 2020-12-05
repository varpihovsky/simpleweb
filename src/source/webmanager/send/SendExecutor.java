package webmanager.send;

import webmanager.database.DatabaseConnector;
import webmanager.database.DatabaseController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendExecutor {
    public String execute(InterfaceSend send, HttpServletRequest request, HttpServletResponse response,
                          ServletContext context, DatabaseController controller) {
        if (send.getClass() == ChangeSend.class) {
            ((ChangeSend) send).setContext(context);
            return send.executeSend(request, response, controller);
        }

        return send.executeSend(request, response, controller);
    }
}
