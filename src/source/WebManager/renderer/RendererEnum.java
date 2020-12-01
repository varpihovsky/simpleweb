package WebManager.renderer;

import WebManager.security.Checker;

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
    },
    NULL {
        {
            this.renderer = new NullRenderer();
        }
    };
    InterfaceRenderer renderer;

    public static RendererEnum getInstance(String s) {
        if (Checker.isContainsWrong(s))
            return RendererEnum.NULL;
        return RendererEnum.valueOf(s.toUpperCase());
    }

    public InterfaceRenderer getCurrentRenderer() {
        return renderer;
    }
}
