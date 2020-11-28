package WebManager.send;

import WebManager.CookieManager;
import WebManager.security.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class ChangeSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request) {
        return null;
    }

    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = request.getParameter("page");
        CookieManager manager = new CookieManager();
        String cookieParam = (String) session.getAttribute("cookie");

        String newEmail = request.getParameter("newemail");
        String newUsername = request.getParameter("newusername");
        String oldUsername = (String) session.getAttribute("username");
        String newPassword = request.getParameter("newpassword");
        String oldPassword = request.getParameter("oldpassword");

        try {
            DataBaseFactory factory = new DataBaseFactory();
            if (oldPassword.equals(factory.getUserData(oldUsername).getPassword())) {
                if (!Checker.isContainsWrong(newEmail)) {
                    factory.changeEmail(oldUsername, newEmail);
                }
                if (!Checker.isContainsWrong(newPassword)) {
                    factory.changePassword(oldUsername, newPassword);
                    session.setAttribute("password", newPassword);
                    if (!Checker.isContainsWrong(cookieParam) &&
                            cookieParam.equals("true"))
                        manager.changePassword(newPassword, response);
                }
                if (!Checker.isContainsWrong(newUsername)) {
                    factory.changeUsername(oldUsername, newUsername);
                    session.setAttribute("username", newUsername);
                    if (!Checker.isContainsWrong(cookieParam) &&
                            cookieParam.equals("true"))
                        manager.changeUsername(newUsername, response);

                    //TODO: change avatar name
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return page;
    }
}
