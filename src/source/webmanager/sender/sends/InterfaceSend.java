package webmanager.sender.sends;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InterfaceSend {
    String executeSend(HttpServletRequest request, HttpServletResponse response);
}
