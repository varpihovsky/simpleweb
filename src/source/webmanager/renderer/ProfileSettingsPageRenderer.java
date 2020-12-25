package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

class ProfileSettingsPageRenderer implements InterfaceRenderer, Operative {
    private DatabaseController databaseController;

    @Override
    public void render(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = databaseController.setOperation(DatabaseController.GET_USER_DATA,
                new User((String) session.getAttribute("username"))).execute();

        if (!Checker.isContainsWrong(user.getPassword()) &&
                user.getPassword().equals(session.getAttribute("password"))) {
            request.setAttribute("email", user.getEmail());
        } else {
            request.setAttribute("username", "Error! Please login again!");
            request.setAttribute("email", "Error! Please login again!");
        }
    }

    @Override
    public void set(HashMap<String, Object> bundle) {
        databaseController = (DatabaseController) bundle.get("DatabaseController");
    }
}
