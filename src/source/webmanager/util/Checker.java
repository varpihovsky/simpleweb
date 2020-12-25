package webmanager.util;

import webmanager.database.DatabaseController;
import webmanager.file.FileManager;
import webmanager.interfaces.Operative;

import java.util.HashMap;

public class Checker {

    public static boolean isContainsWrong(String str) {
        return str == null || str.contains("'") || str.equals("");
    }

    public static boolean checkLength(String str, int from, int to) {
        int temp;
        if (from > to) {
            temp = from;
            from = to;
            to = temp;
        }
        return str.length() >= from && str.length() <= to;
    }

    public static String pageReplace(String page) {
        page = page.replace("/", "");
        page = page.replace(".jsp", "");
        return page;
    }

    public static void initializeObjects(Operative obj, FileManager fileManager, DatabaseController controller) {
        HashMap<String, Object> bundle = new HashMap<>();
        bundle.put("FileManager", fileManager);
        bundle.put("DatabaseController", controller);
        obj.set(bundle);
    }
}
