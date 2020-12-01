package WebManager.renderer;

import WebManager.security.Checker;

import javax.servlet.http.HttpServletRequest;

public class MainPageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        request.setAttribute("navbar", RendererTemplates.renderNavbar(
                (String) request.getSession().getAttribute("username"),
                (String) request.getSession().getAttribute("password")));
    }
}
