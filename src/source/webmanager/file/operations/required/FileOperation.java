package webmanager.file.operations.required;

import javax.servlet.ServletContext;

public interface FileOperation<K, T> {
    K operate(ServletContext context, T operator);
}
