package webmanager.interfaces;

import webmanager.file.FileManager;

import java.util.HashMap;

public class Operative {
    protected FileManager fileManager;

    public void set(HashMap<String, Object> bundle) {
        fileManager = (FileManager) bundle.get("FileManager");
    }
}
