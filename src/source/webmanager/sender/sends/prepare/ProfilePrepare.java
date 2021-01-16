package webmanager.sender.sends.prepare;

import webmanager.database.abstractions.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfilePrepare extends Preparing {

    private final HttpSession session;

    public ProfilePrepare(String currentPage, HttpServletRequest request) {
        super(currentPage, request);
        session = request.getSession();
    }

    @Override
    public String prepare() {
        if (request.getParameter("user") != null || request.getParameter("user").equals("")) {
            request.setAttribute("user",
                    new User.Builder()
                            .withUsername(request.getParameter("user"))
                            .build());
        } else {
            request.setAttribute("user",
                    new User.Builder()
                            .withUsername((String) session.getAttribute("username"))
                            .build());
        }
        return currentPage;
    }
}
