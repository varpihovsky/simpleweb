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
        return (Checker.isContainsWrong(username) || Checker.isContainsWrong(password)) &&
                (Boolean) DatabaseController.getDatabaseAccess(new IsUserExists(), new User.Builder()
                        .withUsername(username)
                        .withPassword(password)
                        .build()).execute();
        //controller.setOperation(DatabaseController.IS_USER_EXISTS, new User(username, password)).execute();
    }
}
