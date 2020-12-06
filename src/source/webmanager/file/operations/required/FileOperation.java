package webmanager.file.operations.required;

import webmanager.file.abstractions.FileOperator;

import javax.servlet.ServletContext;

public interface FileOperation<K> {
    K operate(ServletContext context, FileOperator operator);
}
