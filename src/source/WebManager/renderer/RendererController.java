package WebManager.renderer;

import javax.servlet.http.HttpServletRequest;

public class RendererController {
    public InterfaceRenderer defineRenderer(HttpServletRequest request) {
        InterfaceRenderer currentRenderer = new NullRenderer();
        String page = request.getParameter("page");

        if (page == null || page.equals("")) {
            return currentRenderer;
        }
        try {
            RendererEnum rendererEnum = RendererEnum.valueOf(page.toUpperCase());
            currentRenderer = rendererEnum.getCurrentRenderer();
            return currentRenderer;
        } catch (IllegalArgumentException e) {
            currentRenderer = new NullRenderer();
            return currentRenderer;
        }
    }
}
