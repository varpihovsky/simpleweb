package webmanager;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.IsUserExists;
import webmanager.util.Checker;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class CookieManager {
    Cookie username;
    Cookie password;

    public boolean getCookiesFromRequest(HttpServletRequest request) {
        try {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("username"))
                    username = c;
                if (c.getName().equals("password"))
                    password = c;
            }
            return username != null || password != null;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void setCookiesToResponse(HttpServletResponse response, String username, String password) {
        if (!Checker.isContainsWrong(username) && !Checker.isContainsWrong(password)) {
            response.addCookie(new Cookie("username", username));
            response.addCookie(new Cookie("password", password));
        }
    }

    public boolean checkUser() throws SQLException {
        if (username == null || password == null)
            return false;
        return (Boolean) DatabaseController.getDatabaseAccess(new IsUserExists(), new User.Builder()
                .withUsername(username.getValue())
                .withPassword(password.getValue())
                .build()).execute();
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
        if (username != null || password != null) {
            username.setValue("");
            password.setValue("");
        }
        response.addCookie(new Cookie("username", ""));
        response.addCookie(new Cookie("password", ""));
    }

    public void changeUsername(String username, HttpServletResponse response) {
        this.username = new Cookie("username", username);
        response.addCookie(this.username);
    }

    public void changePassword(String password, HttpServletResponse response) {
        this.password = new Cookie("password", password);
        response.addCookie(this.password);
    }
}
