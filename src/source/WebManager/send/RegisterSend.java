package WebManager.send;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RegisterSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request) {
        String page = request.getParameter("page");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String message = "User is not registered. ";
        boolean isWrong = false;

        if (password.contains("\'")) {
            isWrong = true;
            message = message + "Your email is wrong. ";
        }
        if (username.length() < 4 || username.contains("\'")) {
            isWrong = true;
            message = message + "Your username has length less than 4 or contains wrong characters";
        }
        if (password.length() < 8 || password.length() > 20 || password.contains("\'")) {
            isWrong = true;
            message = message + "Wrong password length.";
        }

        if (isWrong) {
            request.setAttribute("registerMessage", message);
        } else {
            try {
                DataBaseFactory dataBaseFactory = new DataBaseFactory();
                dataBaseFactory.addUser(username, email, password);
                request.setAttribute("registerMessage", "Registered");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        page = page.replace("/", "");
        page = page.replace(".jsp", "");
        return page;
    }
}
