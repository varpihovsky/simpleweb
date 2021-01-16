package webmanager.sender.sends.prepare;

import javax.servlet.http.HttpServletRequest;

public abstract class Preparing {
    protected String currentPage;
    protected HttpServletRequest request;

    public Preparing(String currentPage, HttpServletRequest request) {
        this.request = request;
        this.currentPage = currentPage;

        defaultPrepare();
    }

    public abstract String prepare();

    protected void defaultPrepare() {
    }
}
