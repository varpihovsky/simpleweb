package WebManager.renderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MainPageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String rendered;
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (username == null || username == "" || password == null || password == "") {
            rendered = "Not registered? <a href=\"/controller?page=register&send=redirect\">Register</a><br/>\n" +
                    "    Registered? <a href=\"/controller?page=login&send=redirect\">Login</a>";
        } else rendered = "Your username: ${sessionScope[username]}\n" +
                "    Your password: ${sessionScope[password]}\n" +
                "    Go to profile <a href=\"/controller?page=/profile.jsp&send=redirect\">Redirect</a>";
        request.setAttribute("render", rendered);
    }
}
