package WebManager.file;

import javax.servlet.ServletContext;
import java.io.File;

public class FileManager {
    private ServletContext context;

    public void setContext(ServletContext context) {
        this.context = context;
    }

    public void changeAvatarName(String name, String toName) {
        for (Extensions extension : Extensions.values()) {
            File file = new File(context.getRealPath("/img/useravatars/" +
                    name + "." + extension.name().toLowerCase()));
            file.renameTo(new File(context.getRealPath("/img/useravatars/" +
                    toName + "." + extension.name().toLowerCase())));
        }
    }

    public void changeRoomName(String name, String toName) {
        for (Extensions extension : Extensions.values()) {
            File file = new File(context.getRealPath("/img/roomlogos/" +
                    name + extension.name().toLowerCase()));
            file.renameTo(new File(context.getRealPath("/img/roomlogos/" +
                    toName + extension.name().toLowerCase())));
        }
    }
}
