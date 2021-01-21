package webmanager.file.operations;

import org.apache.log4j.Logger;
import webmanager.file.Extensions;
import webmanager.file.abstractions.PartWriteOperator;
import webmanager.interfaces.FileOperation;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class RoomLogoLoad implements FileOperation<Void, PartWriteOperator> {
    @Override
    public Void operate(ServletContext context, PartWriteOperator operator) {
        for (Extensions extension : Extensions.values()) {
            File file = new File(context.getRealPath("/img/roomlogos/" +
                    operator.getName() + "." + extension.name().toLowerCase()));
            if (file.exists())
                file.delete();
        }

        try {
            Part part = operator.getPart();
            String s = context.getRealPath("/img/roomlogos/" + "/" + operator.getName() +
                    getFileExtension(getFileName(part)));
            part.write(s);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(RoomLogoLoad.class);
            logger.error("IO Exception", e);
        }
        return null;
    }
}
