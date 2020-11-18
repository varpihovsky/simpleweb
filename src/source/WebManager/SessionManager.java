package WebManager;

import WebManager.security.Checker;
import WebManager.send.DataBaseFactory;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SessionManager {
    public static boolean checkUserSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (Checker.isContainsWrong(username) || Checker.isContainsWrong(password))
            return false;
        else {
            try {
                DataBaseFactory dataBaseFactory = new DataBaseFactory();
                return dataBaseFactory.findUser(username, password);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
}
