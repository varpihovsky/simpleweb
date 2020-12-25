package webmanager;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.util.Checker;

import javax.servlet.http.HttpSession;

public class SessionManager {
    public static boolean checkUserSession(HttpSession session, DatabaseController controller) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (Checker.isContainsWrong(username) || Checker.isContainsWrong(password))
            return false;
        return controller.setOperation(DatabaseController.IS_USER_EXISTS, new User(username, password)).execute();
    }
}
