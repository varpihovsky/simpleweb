package webmanager.sender.sends;

import webmanager.Controller;
import webmanager.SessionManager;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.file.FileManager;
import webmanager.file.abstractions.PartWriteOperator;
import webmanager.interfaces.InterfaceSend;
import webmanager.interfaces.Operative;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class RoomCreateSend extends Operative implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        if (!SessionManager.checkUserSession(request.getSession(), databaseController))
            return "login";

        String page = request.getParameter("page");
        try {
            String roomName = request.getParameter("roomName");
            String roomPassword = request.getParameter("roomPassword");
            String roomDescription = request.getParameter("roomDescription");
            String isPrivate = request.getParameter("isPrivate");
            String username = (String) request.getSession().getAttribute("username");
            Part part = request.getPart("roomLogo");


            Room room = new Room(roomName, roomDescription, isPrivate, roomPassword, username);
            room.setAdditionalData("username", username);

            databaseController.setOperation(DatabaseController.CREATE_ROOM, room).execute();
            fileManager.setOperation(FileManager.ROOM_LOGO_LOAD, new PartWriteOperator(part, roomName)).execute();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Controller.logger.warning("IOException:\n\t" + e.getMessage() + "\n\t" + e.getLocalizedMessage() + "\n\t" +
                    e.getCause());
        } catch (ServletException e) {
            Controller.logger.warning("ServletException:\n\t" + e.getMessage() + "\n\t" + e.getLocalizedMessage() + "\n\t" +
                    e.getCause());
        }
        return page;
    }
}
