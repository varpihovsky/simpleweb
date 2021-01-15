package webmanager.interfaces;

import webmanager.file.FileManager;

public class Operative {
    protected FileManager fileManager;

    protected Operative() {
        fileManager = FileManager.getInstance();
    }
}
