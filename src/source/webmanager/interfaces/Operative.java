package webmanager.interfaces;

import webmanager.database.DatabaseController;
import webmanager.file.FileManager;

import java.util.HashMap;

public class Operative {
    protected FileManager fileManager;
    protected DatabaseController databaseController;

    public void set(HashMap<String, Object> bundle) {
        fileManager = (FileManager) bundle.get("FileManager");
        databaseController = (DatabaseController) bundle.get("DatabaseController");
    }
}
