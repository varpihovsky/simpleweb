package webmanager.file.operations;

import webmanager.file.Extensions;
import webmanager.file.abstractions.PartWriteOperator;
import webmanager.file.operations.required.FileOperation;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class UserAvatarLoad implements FileOperation<Void, PartWriteOperator> {

    private String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }

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
        }
        return null;
    }
}
