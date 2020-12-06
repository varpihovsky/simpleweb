package webmanager.file.operations;

import webmanager.file.Extensions;
import webmanager.file.abstractions.FileOperator;
import webmanager.file.operations.required.FileOperation;

import javax.servlet.ServletContext;
import java.io.File;

public class GetUserAvatar implements FileOperation<String> {
    @Override
    public String operate(ServletContext context, FileOperator operator) {
        for (Extensions extension : Extensions.values()) {
            File file = new File(context.getRealPath("/img/useravatars/") + "/" + operator.getName() + "." +
                    extension.toString().toLowerCase());
            if (file.exists())
                return context.getContextPath() + "/img/useravatars/" + file.getName();
        }
        return null;
    }
}
