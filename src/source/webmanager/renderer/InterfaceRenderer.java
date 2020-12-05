package webmanager.renderer;

import webmanager.database.DatabaseController;

import javax.servlet.http.HttpServletRequest;

public interface InterfaceRenderer {
    void render(HttpServletRequest request, DatabaseController controller);
}
