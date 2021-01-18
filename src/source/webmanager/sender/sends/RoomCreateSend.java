package webmanager.sender.sends;

import webmanager.Controller;
import webmanager.SessionManager;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.CreateRoom;
import webmanager.database.operations.GetRoomIdByName;
import webmanager.file.FileManager;
import webmanager.file.abstractions.PartWriteOperator;
import webmanager.interfaces.InterfaceSend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class RoomCreateSend implements InterfaceSend {
    SessionManager sessionManager = new SessionManager();
    FileManager fileManager = FileManager.getInstance();

    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        if (sessionManager.checkUserSession(request.getSession()))
            return "login";

        String page = request.getParameter("page");
        try {
            String roomName = request.getParameter("roomName");
            String roomPassword = request.getParameter("roomPassword");
            String roomDescription = request.getParameter("roomDescription");
            String isPrivate = request.getParameter("isPrivate");
            String username = (String) request.getSession().getAttribute("username");
            Part part = request.getPart("roomLogo");


            Room room = new Room.Builder()
                    .withName(roomName)
                    .withDescription(roomDescription)
                    .withPrivate(isPrivate)
                    .withPassword(roomPassword)
                    .addAdditionalData("username", username)
                    .build();

            new DatabaseController<>(CreateRoom::new, room).execute();

            setImage(part, room);
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

    private void setImage(Part part, Room room) {
        DatabaseController<GetRoomIdByName, Room> databaseController
                = new DatabaseController<>(GetRoomIdByName::new, room);

        room = databaseController.execute();

        fileManager.setOperation(FileManager.ROOM_LOGO_LOAD, new PartWriteOperator(part, String.valueOf(room.getId())))
                .execute();
    }
}
