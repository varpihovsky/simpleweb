package webmanager.sender.sends;

import webmanager.CookieManager;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.security.Checker;
import webmanager.database.operations.required.DatabaseCommunicative;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginSend implements InterfaceSend, DatabaseCommunicative {
    private DatabaseController controller;

    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        CookieManager manager = new CookieManager();
        String page = request.getParameter("page");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String message = "";
        if (Checker.isContainsWrong(username) || Checker.isContainsWrong(password) || !Checker.checkLength(username, 4, 20) ||
                !Checker.checkLength(password, 8, 20)) {
            message += "Wrong username or password";
        } else {
            if (controller.setOperation(DatabaseController.IS_USER_EXISTS, new User(username, password)).execute()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                if (!Checker.isContainsWrong(request.getParameter("cookie"))) {
                    manager.setCookiesToResponse(response, username, password);
                    session.setAttribute("cookie", request.getParameter("cookie"));
                }
                page = "profile";
            } else message += "User is not exist's";
        }

        request.setAttribute("loginMessage", message);
        return Checker.pageReplace(page);
    }

    @Override
    public void setController(DatabaseController controller) {
        this.controller = controller;
    }
}
