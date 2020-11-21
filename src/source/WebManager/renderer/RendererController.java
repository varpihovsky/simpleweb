package WebManager.renderer;

public class RendererController {
    public InterfaceRenderer defineRenderer(String page) {
        InterfaceRenderer currentRenderer = new NullRenderer();

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
