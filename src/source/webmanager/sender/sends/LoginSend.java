package webmanager.sender.sends;

import webmanager.CookieManager;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.IsUserExists;
import webmanager.interfaces.InterfaceSend;
import webmanager.sender.sends.prepare.PrepareFactory;
import webmanager.sender.sends.prepare.Preparing;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginSend implements InterfaceSend {
    CookieManager cookieManager = new CookieManager();

    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String message = "";
        if (Checker.isContainsWrong(username) || Checker.isContainsWrong(password) || !Checker.checkLength(username, 4, 20) ||
                !Checker.checkLength(password, 8, 20)) {
            message += "Wrong username or password";
        } else {
            if ((Boolean) DatabaseController.getDatabaseAccess(new IsUserExists(), new User.Builder()
                    .withUsername(username)
                    .withPassword(password)
                    .build()).execute()
            ) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                if (!Checker.isContainsWrong(request.getParameter("cookie"))) {
                    cookieManager.setCookiesToResponse(response, username, password);
                    session.setAttribute("cookie", request.getParameter("cookie"));
                }
                page = "profile";
            } else message += "User is not exist's";
        }

        request.setAttribute("loginMessage", message);
        request.setAttribute("user", new User.Builder().withUsername(username).build());

        PrepareFactory prepareFactory = new PrepareFactory();
        Preparing preparing = prepareFactory.getPrepare(page, request);

        return preparing.prepare();
    }
}
