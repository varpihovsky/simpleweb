package webmanager.sender.sends;

import webmanager.CookieManager;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.GetUserData;
import webmanager.database.operations.IsUserExists;
import webmanager.interfaces.InterfaceSend;
import webmanager.sender.sends.prepare.PrepareFactory;
import webmanager.sender.sends.prepare.Preparing;

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

        User user = new User.Builder().withUsername(username).withPassword(String.valueOf(password.hashCode())).build();
        DatabaseController<IsUserExists, User> databaseController =
                new DatabaseController<>(IsUserExists::new, user);

        if (databaseController.execute()) {
            HttpSession session = request.getSession();

            initializeSession(session, user);

            if (request.getParameter("cookie") != null) {
                initializeCookie(response, user);
            }
            page = "profile";
        }

        PrepareFactory prepareFactory = new PrepareFactory();
        Preparing preparing = prepareFactory.getPrepare(page, request);

        //TODO: fix bug where page reloading redirects to main page after login
        return preparing.prepare();
    }

    private void initializeSession(HttpSession session, User user) {
        user = getUserData(user);

        session.setAttribute("currentUser", user);
    }

    private void initializeCookie(HttpServletResponse response, User user) {
        user = getUserData(user);

        cookieManager.setCookiesToResponse(response, user);
    }

    private User getUserData(User user) {
        DatabaseController<GetUserData, User> getUserDataDatabaseController =
                new DatabaseController<>(GetUserData::new, user);
        return getUserDataDatabaseController.execute();
    }
}
