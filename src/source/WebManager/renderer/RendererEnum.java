package WebManager.renderer;

public enum RendererEnum {
    MAIN {
        {
            this.renderer = new MainPageRenderer();
        }
    },
    PROFILE {
        {
            this.renderer = new ProfilePageRenderer();
        }
    };
    InterfaceRenderer renderer;

    public InterfaceRenderer getCurrentRenderer() {
        return renderer;
    }
}
