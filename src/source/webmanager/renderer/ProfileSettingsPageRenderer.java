package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class ProfileSettingsPageRenderer extends Operative implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = databaseController.setOperation(DatabaseController.GET_USER_DATA,
                new User((String) session.getAttribute("username"))).execute();

        if (user.getUsername().equals(session.getAttribute("username")) &&
                Integer.parseInt(user.getPassword()) == session.getAttribute("password").hashCode()) {
            request.setAttribute("username", session.getAttribute("username"));
            request.setAttribute("email", user.getEmail());
        } else {
            request.setAttribute("username", "Error! Please login again!");
            request.setAttribute("email", "Error! Please login again!");
        }
    }
}
