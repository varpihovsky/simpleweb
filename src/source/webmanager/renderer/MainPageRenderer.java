package webmanager.renderer;

import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

class MainPageRenderer implements InterfaceRenderer, Operative {
    @Override
    public void render(HttpServletRequest request) {
        request.setAttribute("navbar", RendererTemplates.renderNavbar(
                (String) request.getSession().getAttribute("username"),
                (String) request.getSession().getAttribute("password"),
                request));
    }

    @Override
    public void set(HashMap<String, Object> bundle) {

    }
}
