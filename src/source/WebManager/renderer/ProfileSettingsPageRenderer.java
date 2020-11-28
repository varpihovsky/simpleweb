package WebManager.renderer;

import WebManager.security.Checker;
import WebManager.send.DataBaseFactory;
import WebManager.send.dbabstractions.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class ProfileSettingsPageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        User user = new User();
        HttpSession session = request.getSession();
        try {
            DataBaseFactory factory = new DataBaseFactory();
            user = factory.getUserData((String) session.getAttribute("username"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (!Checker.isContainsWrong(user.getPassword()) &&
                user.getPassword().equals(session.getAttribute("password"))) {
            request.setAttribute("email", user.getEmail());
        } else {
            request.setAttribute("username", "Error! Please login again!");
            request.setAttribute("email", "Error! Please login again!");
        }
    }
}
