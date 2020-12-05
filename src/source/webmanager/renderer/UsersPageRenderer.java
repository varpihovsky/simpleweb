package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.security.Checker;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

class UsersPageRenderer implements InterfaceRenderer {
    private final static int USERS_NUM = 30;

    @Override
    public void render(HttpServletRequest request, DatabaseController controller) {
        String render = "";
        String username = request.getParameter("user");

        ArrayList<User> users;
        User user;

        if (!Checker.isContainsWrong(username)) {
            user = new User(username);
            user.setAdditionalData("num", USERS_NUM);

            users = controller.setOperation(DatabaseController.FIND_USER,
                    user).execute();
        } else {
            user = new User("");
            users = controller.setOperation(DatabaseController.FIND_USER,
                    user).execute();
        }

        if (users != null)
            for (User usr : users)
                render += "<a href=\"#\">\n" +
                        "                <div class=\"user\">\n" +
                        "                    <h4>" + usr.getUsername() + "</h4>\n" +
                        "                    <img src=\"img/useravatars/" + usr.getUsername() + ".jpg\" alt=\"room logo\"/>\n" +
                        "                </div>\n" +
                        "            </a>\n";


        request.setAttribute("render", render);
        request.setAttribute("navbar", RendererTemplates.renderNavbar(
                (String) request.getSession().getAttribute("username"),
                (String) request.getSession().getAttribute("password")));
    }
}
