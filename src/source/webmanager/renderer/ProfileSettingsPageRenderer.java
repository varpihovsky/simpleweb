package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.GetUserData;
import webmanager.interfaces.InterfaceRenderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class ProfileSettingsPageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user =
                (User) DatabaseController.getDatabaseAccess(new GetUserData(),
                        new User.Builder()
                                .withUsername((String) session.getAttribute("username"))
                                .build()).execute();
        //databaseController.setOperation(DatabaseController.GET_USER_DATA, new User((String) session.getAttribute("username"))).execute();

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
