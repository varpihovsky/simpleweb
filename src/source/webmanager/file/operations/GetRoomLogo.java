package webmanager.file.operations;

import webmanager.file.Extensions;
import webmanager.file.abstractions.RenameOperator;
import webmanager.interfaces.FileOperation;

import javax.servlet.ServletContext;
import java.io.File;

public class GetRoomLogo implements FileOperation<String, RenameOperator> {
    @Override
    public String operate(ServletContext context, RenameOperator operator) {
        for (Extensions extension : Extensions.values()) {
            File file = new File(context.getRealPath("/img/roomlogos/") + "/" + operator.getName() + "." +
                    extension.toString().toLowerCase());
            if (file.exists())
                return context.getContextPath() + "/img/roomlogos/" + file.getName();
        }
        return null;
    }
}
