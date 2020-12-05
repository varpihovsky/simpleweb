package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.security.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class ProfileSettingsPageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request, DatabaseController controller) {
        HttpSession session = request.getSession();
        User user = controller.setOperation(DatabaseController.GET_USER_DATA,
                new User((String) session.getAttribute("username"))).execute();

        if (!Checker.isContainsWrong(user.getPassword()) &&
                user.getPassword().equals(session.getAttribute("password"))) {
            request.setAttribute("email", user.getEmail());
        } else {
            request.setAttribute("username", "Error! Please login again!");
            request.setAttribute("email", "Error! Please login again!");
        }
    }
}
