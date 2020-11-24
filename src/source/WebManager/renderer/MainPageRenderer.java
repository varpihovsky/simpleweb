package WebManager.renderer;

import javax.servlet.http.HttpServletRequest;

public class MainPageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        String rendered;
        String username = (String) request.getSession().getAttribute("username");
        String password = (String) request.getSession().getAttribute("password");
        if (username == null || password == null || username.equals("") || password.equals("")) {
            rendered = "<a href=\"#\">Users</a>\n" +
                    "            <a href=\"/controller?page=register&send=redirect\">Register</a>\n" +
                    "            <a href=\"#\">News</a>\n" +
                    "            <a href=\"#\">Rooms</a>\n" +
                    "            <a href=\"/controller?page=login&send=redirect\">Login</a>";
        } else rendered = "<a href=\"#\">Users</a>\n" +
                "            <a href=\"/controller?page=profile&send=redirect\">Profile</a>\n" +
                "            <a href=\"#\">News</a>\n" +
                "            <a href=\"#\">Rooms</a>\n" +
                "            <a href=\"/controller?page=main&send=logout\">Logout</a>";
        request.setAttribute("render", rendered);
    }
}
