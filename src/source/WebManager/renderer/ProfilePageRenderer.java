package WebManager.renderer;

import javax.servlet.http.HttpServletRequest;

public class ProfilePageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        String rendered = "";
        request.setAttribute("render", rendered);
    }
}
