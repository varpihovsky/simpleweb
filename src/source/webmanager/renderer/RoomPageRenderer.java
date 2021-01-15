package webmanager.renderer;


import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;

import javax.servlet.http.HttpServletRequest;

public class RoomPageRenderer extends Operative implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        String roomName = request.getParameter("room");
        String roomPassword = request.getParameter("roomPassword");
    }
}
