package WebManager.send;

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
        boolean isWrong = false;
        if (username.contains("\'") || password.contains("\'")) {
            isWrong = true;
            message += "Wrong username or email symbols typed";
        }
        if (!isWrong) {
            try {
                DataBaseFactory dataBaseFactory = new DataBaseFactory();
                isWrong = !dataBaseFactory.findUser(username, password);
                if (!isWrong) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("password", password);
                    page = "/profile.jsp";
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        request.setAttribute("loginMessage", message);
        return page;
    }
}
