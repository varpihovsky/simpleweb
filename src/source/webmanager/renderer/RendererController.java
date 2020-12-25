package webmanager.renderer;

import webmanager.interfaces.InterfaceRenderer;

public class RendererController {
    public InterfaceRenderer defineRenderer(String page) {
        InterfaceRenderer currentRenderer;

        try {
            RendererEnum rendererEnum = RendererEnum.getInstance(page);
            currentRenderer = rendererEnum.getCurrentRenderer();
            return currentRenderer;
        } catch (IllegalArgumentException e) {
            currentRenderer = new NullRenderer();
            return currentRenderer;
        }
    }
}
