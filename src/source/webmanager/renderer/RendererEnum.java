package webmanager.renderer;

import webmanager.security.Checker;

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
    },
    USERS {
        {
            this.renderer = new UsersPageRenderer();
        }
    },
    ROOMS {
        {
            this.renderer = new RoomsPageRenderer();
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
