package WebManager.send;

import WebManager.security.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegisterSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request) {
        String page = request.getParameter("page");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String message = "User is not registered. ";

        if (Checker.isContainsWrong(username) || Checker.isContainsWrong(email) || Checker.isContainsWrong(password) ||
                !Checker.checkLength(username, 4, 20) || !Checker.checkLength(password, 8, 20) ||
                !Checker.checkLength(email, 0, 40)) {
            message += "Contains wrong symbols or has wrong length";
        } else {
            try {
                DataBaseFactory dataBaseFactory = new DataBaseFactory();
                dataBaseFactory.addUser(username, email, password);
                message = "User registered";
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        request.setAttribute("registerMessage", message);
        return Checker.pageReplace(page);
    }

    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
