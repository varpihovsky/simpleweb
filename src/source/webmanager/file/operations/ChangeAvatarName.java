package webmanager.file.operations;

import webmanager.file.Extensions;
import webmanager.file.abstractions.FileOperator;
import webmanager.file.operations.required.FileOperation;

import javax.servlet.ServletContext;
import java.io.File;

public class ChangeAvatarName implements FileOperation<Void> {
    @Override
    public Void operate(ServletContext context, FileOperator operator) {
        for (Extensions extension : Extensions.values()) {
            File file = new File(context.getRealPath("/img/useravatars/" +
                    operator.getName() + "." + extension.name().toLowerCase()));
            file.renameTo(new File(context.getRealPath("/img/useravatars/" +
                    operator.getToName() + "." + extension.name().toLowerCase())));
        }
        return null;
    }
}
