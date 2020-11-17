package WebManager;

import WebManager.send.DataBaseFactory;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SessionManager {
    public static boolean checkUserSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        boolean isWrong = false;
        if (username == null || password == null || username.contains("\'") || password.contains("\'")) {
            isWrong = true;
        }
        if (!isWrong) {
            try {
                DataBaseFactory dataBaseFactory = new DataBaseFactory();
                isWrong = dataBaseFactory.findUser(username, password);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return !isWrong;
    }
}
