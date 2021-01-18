package webmanager;

import webmanager.database.abstractions.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
    private final static int MAX_AGE = 2000;

    Cookie username;
    Cookie password;
    Cookie email;
    Cookie id;

    public boolean getCookiesFromRequest(HttpServletRequest request) {
        try {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("username"))
                    username = c;
                if (c.getName().equals("password"))
                    password = c;
                if (c.getName().equals("email"))
                    email = c;
                if (c.getName().equals("id"))
                    id = c;
            }
            return username != null && password != null && email != null && id != null;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void setCookiesToResponse(HttpServletResponse response, User user) {
        username = new Cookie("username", user.getUsername());
        password = new Cookie("password", user.getPassword());
        email = new Cookie("email", user.getEmail());
        id = new Cookie("id", String.valueOf(user.getId()));

        username.setMaxAge(MAX_AGE);
        password.setMaxAge(MAX_AGE);
        email.setMaxAge(MAX_AGE);
        id.setMaxAge(MAX_AGE);

        response.addCookie(username);
        response.addCookie(password);
        response.addCookie(email);
        response.addCookie(id);
    }

    public void deleteCookies(HttpServletResponse response) {
        response.addCookie(new Cookie("username", ""));
        response.addCookie(new Cookie("password", ""));
        response.addCookie(new Cookie("email", ""));
        response.addCookie(new Cookie("id", ""));
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
