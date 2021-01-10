package webmanager;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.IsUserExists;
import webmanager.util.Checker;

import javax.servlet.http.HttpSession;

public class SessionManager {
    public static boolean checkUserSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (Checker.isContainsWrong(username) || Checker.isContainsWrong(password))
            return false;
        return (Boolean) DatabaseController.getDatabaseAccess(new IsUserExists(), new User(username, password)).execute();
        //controller.setOperation(DatabaseController.IS_USER_EXISTS, new User(username, password)).execute();
    }
}
