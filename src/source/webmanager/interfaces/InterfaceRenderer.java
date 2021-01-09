package webmanager.interfaces;

import javax.servlet.http.HttpServletRequest;

public interface InterfaceRenderer {
    default void render(HttpServletRequest request) {

    }
}
