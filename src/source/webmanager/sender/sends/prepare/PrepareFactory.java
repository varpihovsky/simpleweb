package webmanager.sender.sends.prepare;

import javax.servlet.http.HttpServletRequest;

public class PrepareFactory {
    public Preparing getPrepare(String currentPage, HttpServletRequest request) {
        Preparing preparing;
        switch (currentPage) {
            case "login":
                preparing = new LoginPagePrepare(currentPage, request);
                break;
            case "profile":
                preparing = new ProfilePrepare(currentPage, request);
                break;
            case "profileSettings":
                preparing = new ProfileSettingsPrepare(currentPage, request);
                break;
            default:
                preparing = new DefaultPrepare(currentPage, request);
        }
        return preparing;
    }
}
