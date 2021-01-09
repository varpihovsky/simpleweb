package webmanager.renderer;

import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;

import javax.servlet.http.HttpServletRequest;

class MainPageRenderer extends Operative implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        request.setAttribute("navbar", RendererTemplates.renderNavbar(
                (String) request.getSession().getAttribute("username"),
                (String) request.getSession().getAttribute("password"),
                request));
    }

}
