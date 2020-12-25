package webmanager.renderer;

import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

class NullRenderer implements InterfaceRenderer, Operative {
    @Override
    public void render(HttpServletRequest request) {

    }

    @Override
    public void set(HashMap<String, Object> bundle) {

    }
}
