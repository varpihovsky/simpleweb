package webmanager.jstl;

import webmanager.Controller;
import webmanager.database.abstractions.User;
import webmanager.renderer.RendererTemplates;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class NavbarTag extends SimpleTagSupport {
    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void doTag() throws JspException {
        try {
            getJspContext().getOut().write(
                    RendererTemplates.renderNavbar(
                            currentUser.getUsername(),
                            currentUser.getPassword(),
                            (String) currentUser.getAdditionalData("contextPath")));
        } catch (IOException e) {
            Controller.logger.severe(e.getMessage());
            throw new SkipPageException(e);
        }
    }
}
