package WebManager.renderer;

import WebManager.security.Checker;

public class RendererTemplates {
    static final String NAVBAR_LOGINED =
            "<a href=\"/controller?page=users&send=redirect\">Users</a>\n" +
                    "<a href=\"/controller?page=register&send=redirect\">Register</a>\n" +
                    "<a href=\"#\">News</a>\n" +
                    "<a href=\"#\">Rooms</a>\n" +
                    "<a href=\"/controller?page=login&send=redirect\">Login</a>";

    static final String NAVBAR_NON_LOGINED =
            "<a href=\"/controller?page=users&send=redirect\">Users</a>\n" +
                    "<a href=\"/controller?page=profile&send=redirect\">Profile</a>\n" +
                    "<a href=\"#\">News</a>\n" +
                    "<a href=\"#\">Rooms</a>\n" +
                    "<a href=\"/controller?page=main&send=logout\">Logout</a>";

    static String renderNavbar(String username, String password) {
        if (!Checker.isContainsWrong(username) && !Checker.isContainsWrong(password))
            return RendererTemplates.NAVBAR_LOGINED;
        return RendererTemplates.NAVBAR_NON_LOGINED;
    }
}
