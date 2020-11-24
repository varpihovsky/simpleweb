package WebManager.renderer;

import WebManager.send.DataBaseFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ProfilePageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        request.setAttribute("username", username);
    }
}
