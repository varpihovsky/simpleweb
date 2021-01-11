package webmanager.jstl;

import webmanager.Controller;
import webmanager.renderer.RendererTemplates;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class NavbarTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        try {
            getJspContext().getOut().write(
                    RendererTemplates.renderNavbar(
                            (String) getJspContext().getAttribute("username"),
                            (String) getJspContext().getAttribute("password"),
                            (String) getJspContext().getAttribute("contextPath")));
        } catch (IOException e) {
            Controller.logger.severe(e.getMessage());
            throw new SkipPageException(e);
        }
    }
}
