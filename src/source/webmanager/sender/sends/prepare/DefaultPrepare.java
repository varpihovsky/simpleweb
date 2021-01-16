package webmanager.sender.sends.prepare;

import javax.servlet.http.HttpServletRequest;

public class DefaultPrepare extends Preparing {
    public DefaultPrepare(String page, HttpServletRequest request) {
        super(page, request);
    }

    @Override
    public String prepare() {
        return currentPage;
    }
}
