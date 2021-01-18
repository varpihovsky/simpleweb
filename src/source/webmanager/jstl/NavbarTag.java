package webmanager.jstl;

import webmanager.Controller;
import webmanager.database.abstractions.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class NavbarTag extends SimpleTagSupport {
    private User currentUser;
    private String contextPath;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    @Override
    public void doTag() throws JspException {
        try {
            getJspContext().getOut().write(
                    renderNavbar(
                            currentUser.getUsername(),
                            currentUser.getPassword(),
                            contextPath));
        } catch (IOException e) {
            Controller.logger.severe(e.getMessage());
            throw new SkipPageException(e);
        }
    }

    private String renderNavbar(String username, String password, String contextPath) {
        if (contextPath == null)
            contextPath = "";
        if (username != null && password != null)
            return "<a href=\"" + contextPath + "/controller?page=users&send=redirect\">Users</a>\n" +
                    "<a href=\"" + contextPath + "/controller?page=profile&send=redirect\">Profile</a>\n" +
                    "<a href=\"#\">News</a>\n" +
                    "<a href=\"" + contextPath + "/controller?page=rooms&send=redirect\">Rooms</a>\n" +
                    "<a href=\"" + contextPath + "/controller?page=main&send=logout\">Logout</a>";
        return "<a href=\"" + contextPath + "/controller?page=users&send=redirect\">Users</a>\n" +
                "<a href=\"" + contextPath + "/controller?page=register&send=redirect\">Register</a>\n" +
                "<a href=\"#\">News</a>\n" +
                "<a href=\"" + contextPath + "/controller?page=rooms&send=redirect\">Rooms</a>\n" +
                "<a href=\"" + contextPath + "/controller?page=login&send=redirect\">Login</a>";

    }
}
