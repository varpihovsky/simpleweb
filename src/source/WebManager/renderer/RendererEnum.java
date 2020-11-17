package WebManager.renderer;

public enum RendererEnum {
    MAIN {
        {
            this.renderer = new MainPageRenderer();
        }
    };
    InterfaceRenderer renderer;

    public InterfaceRenderer getCurrentRenderer() {
        return renderer;
    }
}
