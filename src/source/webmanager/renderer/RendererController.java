package webmanager.renderer;

public class RendererController {
    public InterfaceRenderer defineRenderer(String page) {
        InterfaceRenderer currentRenderer = new NullRenderer();

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
