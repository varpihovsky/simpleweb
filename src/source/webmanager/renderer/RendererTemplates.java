package webmanager.renderer;

import webmanager.util.Checker;

public class RendererTemplates {
    public static String renderNavbar(String username, String password, String contextPath) {
        if (contextPath == null)
            contextPath = "";
        if (Checker.isContainsWrong(username) && Checker.isContainsWrong(password))
            return "<a href=\"" + contextPath + "/controller?page=users&send=redirect\">Users</a>\n" +
                    "<a href=\"" + contextPath + "/controller?page=register&send=redirect\">Register</a>\n" +
                    "<a href=\"#\">News</a>\n" +
                    "<a href=\"" + contextPath + "/controller?page=rooms&send=redirect\">Rooms</a>\n" +
                    "<a href=\"" + contextPath + "/controller?page=login&send=redirect\">Login</a>";
        return "<a href=\"" + contextPath + "/controller?page=users&send=redirect\">Users</a>\n" +
                "<a href=\"" + contextPath + "/controller?page=profile&send=redirect\">Profile</a>\n" +
                "<a href=\"#\">News</a>\n" +
                "<a href=\"" + contextPath + "/controller?page=rooms&send=redirect\">Rooms</a>\n" +
                "<a href=\"" + contextPath + "/controller?page=main&send=logout\">Logout</a>";
    }
}
