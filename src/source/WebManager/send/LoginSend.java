package WebManager.send;

import WebManager.SessionManager;
import WebManager.security.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LoginSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request) {
        String page = request.getParameter("page");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String message = "";
        if (Checker.isContainsWrong(username) || Checker.isContainsWrong(password) || !Checker.checkLength(username, 4, 20) ||
                !Checker.checkLength(password, 8, 20)) {
            message += "Wrong username or password";
        } else {
            try {
                DataBaseFactory dataBaseFactory = new DataBaseFactory();
                if (dataBaseFactory.findUser(username, password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("password", password);
                    page = "profile";
                } else message += "User is not exist's";
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        request.setAttribute("loginMessage", message);
        return Checker.pageReplace(page);
    }
}
