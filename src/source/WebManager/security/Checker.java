package WebManager.security;

public class Checker {

    public static boolean isContainsWrong(String str) {
        if (str == null || str.contains("\'") || str.equals(""))
            return true;
        return false;
    }

    public static boolean checkLength(String str, int from, int to) {
        int temp;
        if (from > to) {
            temp = from;
            from = to;
            to = temp;
        }
        if (str.length() >= from && str.length() <= to)
            return true;
        return false;
    }

    public static String pageReplace(String page) {
        page = page.replace("/", "");
        page = page.replace(".jsp", "");
        return page;
    }
}
