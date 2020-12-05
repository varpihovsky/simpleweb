package webmanager.security;

public class Checker {

    public static boolean isContainsWrong(String str) {
        return str == null || str.contains("\'") || str.equals("");
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
}
