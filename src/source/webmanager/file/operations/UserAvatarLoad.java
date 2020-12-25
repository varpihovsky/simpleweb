package webmanager.file.operations;

import webmanager.Controller;
import webmanager.file.Extensions;
import webmanager.file.abstractions.PartWriteOperator;
import webmanager.interfaces.FileOperation;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class UserAvatarLoad implements FileOperation<Void, PartWriteOperator> {

    @Override
    public Void operate(ServletContext context, PartWriteOperator operator) {
        for (Extensions extension : Extensions.values()) {
            File file = new File(context.getRealPath("/img/useravatars/" +
                    operator.getName() + "." + extension.name().toLowerCase()));
            if (file.exists())
                file.delete();
        }

        try {
            Part part = operator.getPart();
            String s = context.getRealPath("/img/useravatars/" + "/" + operator.getName() +
                    getFileExtension(getFileName(part)));
            part.write(s);
        } catch (IOException e) {
            System.out.printf(e.getMessage());
            Controller.logger.warning("IOException:\n\t" + e.getMessage() + "\n\t" + e.getLocalizedMessage() + "\n\t" +
                    e.getCause());
        }
        return null;
    }
}
