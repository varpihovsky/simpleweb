package WebManager.send;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

abstract class Changer {
    private ServletContext context;

    public ServletContext getServletContext() {
        return context;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
