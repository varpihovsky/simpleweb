package WebManager.renderer;

import WebManager.security.Checker;
import WebManager.send.DataBaseFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersPageRenderer implements InterfaceRenderer {
    private final int USERS_NUM = 30;

    @Override
    public void render(HttpServletRequest request) {
        String render = "";
        String user = request.getParameter("user");

        DataBaseFactory factory = null;
        ArrayList<String> usernames = null;

        try {
            factory = new DataBaseFactory();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (Checker.isContainsWrong(user)) {
            try {
                usernames = factory.userFind("", USERS_NUM);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                usernames = factory.userFind(user, USERS_NUM);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (usernames != null)
            for (String username : usernames)
                render += "<a href=\"#\">\n" +
                        "                <div class=\"user\">\n" +
                        "                    <h4>" + username + "</h4>\n" +
                        "                    <img src=\"img/useravatars/" + username + ".jpg\" alt=\"room logo\"/>\n" +
                        "                </div>\n" +
                        "            </a>\n";


        request.setAttribute("render", render);
        request.setAttribute("navbar", RendererTemplates.renderNavbar(
                (String) request.getSession().getAttribute("username"),
                (String) request.getSession().getAttribute("password")));
    }
}
