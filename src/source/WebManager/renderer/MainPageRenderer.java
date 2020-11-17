package WebManager.renderer;

import javax.servlet.http.HttpServletRequest;

public class MainPageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        String rendered;
        String username = (String) request.getSession().getAttribute("username");
        String password = (String) request.getSession().getAttribute("password");
        if (username == null || password == null || username.equals("") || password.equals("")) {
            rendered = "Not registered? <a href=\"/controller?page=register&send=redirect\">Register</a><br/>\n" +
                    "    Registered? <a href=\"/controller?page=login&send=redirect\">Login</a>";
        } else rendered = "Your username: " + username + "\n<br/>" +
                "    Your password: " + password + "\n<br/>" +
                "    <a href=\"/controller?page=profile&send=redirect\">Go to profile</a><br/>";
        request.setAttribute("render", rendered);
    }
}
