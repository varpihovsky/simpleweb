package webmanager.renderer;

import javax.servlet.http.HttpServletRequest;

class MainPageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        request.setAttribute("navbar", RendererTemplates.renderNavbar(
                (String) request.getSession().getAttribute("username"),
                (String) request.getSession().getAttribute("password"),
                request));
    }
}
