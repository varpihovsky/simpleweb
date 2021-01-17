package webmanager.jstl;

import webmanager.database.abstractions.Room;
import webmanager.file.FileManager;
import webmanager.file.abstractions.RenameOperator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class GetRoomLogoByRoom extends SimpleTagSupport {
    FileManager fileManager = FileManager.getInstance();

    private Room room;

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public void doTag() throws JspException, IOException {
        getJspContext()
                .getOut()
                .write((String) fileManager.setOperation(FileManager.GET_ROOM_LOGO,
                        new RenameOperator(String.valueOf(room.getId())))
                        .execute());
    }
}
