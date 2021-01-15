package webmanager.renderer;

import webmanager.interfaces.InterfaceRenderer;

import javax.servlet.http.HttpServletRequest;

public class RenderExecutor {
    private static InterfaceRenderer defineRenderer(String page) {
        InterfaceRenderer currentRenderer = new NullRenderer();

        try {
            RendererEnum rendererEnum = RendererEnum.getInstance(page);
            currentRenderer = rendererEnum.getCurrentRenderer();
            return currentRenderer;
        } catch (IllegalArgumentException e) {
            return currentRenderer;
        }
    }

    public static void execute(String page, HttpServletRequest request) {
        InterfaceRenderer renderer = defineRenderer(page);

        renderer.render(request);
    }
}
