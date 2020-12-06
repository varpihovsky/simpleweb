package webmanager.renderer;

import webmanager.database.DatabaseController;

import javax.servlet.http.HttpServletRequest;

class NullRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {

    }
}
