package webmanager.send;

import webmanager.database.DatabaseConnector;
import webmanager.database.DatabaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InterfaceSend {


    String executeSend(HttpServletRequest request, HttpServletResponse response, DatabaseController controller);
}
