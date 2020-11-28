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
    },
    PROFILESETTINGS {
        {
            this.renderer = new ProfileSettingsPageRenderer();
        }
    };
    InterfaceRenderer renderer;

    public InterfaceRenderer getCurrentRenderer() {
        return renderer;
    }
}
