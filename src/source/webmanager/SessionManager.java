package webmanager;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.IsUserExists;

import javax.servlet.http.HttpSession;

public class SessionManager {
    public boolean checkUserSession(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        DatabaseController<IsUserExists, User> databaseController =
                new DatabaseController<>(IsUserExists::new, currentUser);

        return databaseController.execute();
    }
}
