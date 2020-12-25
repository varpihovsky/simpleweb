package webmanager.renderer;

import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;

class RendererTemplates {

    static String renderNavbar(String username, String password, HttpServletRequest request) {
        if (Checker.isContainsWrong(username) && Checker.isContainsWrong(password))
            return "<a href=\"" + request.getContextPath() + "/controller?page=users&send=redirect\">Users</a>\n" +
                    "<a href=\"" + request.getContextPath() + "/controller?page=register&send=redirect\">Register</a>\n" +
                    "<a href=\"#\">News</a>\n" +
                    "<a href=\"" + request.getContextPath() + "/controller?page=rooms&send=redirect\">Rooms</a>\n" +
                    "<a href=\"" + request.getContextPath() + "/controller?page=login&send=redirect\">Login</a>";
        return "<a href=\"" + request.getContextPath() + "/controller?page=users&send=redirect\">Users</a>\n" +
                "<a href=\"" + request.getContextPath() + "/controller?page=profile&send=redirect\">Profile</a>\n" +
                "<a href=\"#\">News</a>\n" +
                "<a href=\"" + request.getContextPath() + "/controller?page=rooms&send=redirect\">Rooms</a>\n" +
                "<a href=\"" + request.getContextPath() + "/controller?page=main&send=logout\">Logout</a>";
    }
}
