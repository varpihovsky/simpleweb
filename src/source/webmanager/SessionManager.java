package webmanager;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.IsUserExists;
import webmanager.util.Checker;

import javax.servlet.http.HttpSession;

public class SessionManager {
    public boolean checkUserSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        return (Checker.isContainsWrong(username) || Checker.isContainsWrong(password)) &&
                (Boolean) DatabaseController.getDatabaseAccess(new IsUserExists(), new User.Builder()
                        .withUsername(username)
                        .withPassword(password)
                        .build()).execute();
    }
}
