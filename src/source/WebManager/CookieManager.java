package WebManager;

import WebManager.send.DataBaseFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class CookieManager {
    Cookie username;
    Cookie password;

    public boolean getCookiesFromRequest(HttpServletRequest request) {
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals("username"))
                username = c;
            if (c.getName().equals("password"))
                password = c;
        }
        if (username == null || password == null)
            return false;
        else return true;
    }

    public void setCookiesToResponse(HttpServletResponse response, String username, String password) {
        response.addCookie(new Cookie("username", username));
        response.addCookie(new Cookie("password", password));
    }

    public boolean checkUser() throws SQLException {
        if (username == null || password == null)
            return false;
        DataBaseFactory factory = new DataBaseFactory();
        return factory.findUser(username.getValue(), password.getValue());
    }

    public boolean createSessionFromCookie(HttpSession session) {
        try {
            if (checkUser()) {
                session.setAttribute("username", username.getValue());
                session.setAttribute("password", password.getValue());
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public void deleteCookies(HttpServletResponse response) {
        username.setValue("");
        password.setValue("");

        response.addCookie(username);
        response.addCookie(password);
    }
}
