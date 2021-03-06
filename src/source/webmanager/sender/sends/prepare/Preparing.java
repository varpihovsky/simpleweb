package webmanager.sender.sends.prepare;

import webmanager.database.abstractions.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class Preparing {
    protected String currentPage;
    protected HttpServletRequest request;
    private final HttpSession session;

    public Preparing(String currentPage, HttpServletRequest request) {
        this.request = request;
        this.currentPage = currentPage;

        session = request.getSession();
        defaultPrepare();
    }

    public abstract String prepare();

    private void defaultPrepare() {
        User user = (User) session.getAttribute("currentUser");

        if (user == null)
            user = new User.Builder().build();

        request.setAttribute("currentUser", user);
    }
}
