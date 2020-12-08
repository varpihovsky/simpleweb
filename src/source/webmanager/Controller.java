package webmanager;

import webmanager.database.DatabaseConnector;
import webmanager.database.DatabaseController;
import webmanager.file.FileManager;
import webmanager.properties.PropertyManager;
import webmanager.renderer.RenderExecutor;
import webmanager.sender.SendExecutor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
@MultipartConfig
public class Controller extends HttpServlet {
    private static DatabaseConnector connector;
    private static DatabaseController databaseController;
    private FileManager fileManager;
    private static PropertyManager propertyManager;

    @Override
    public void init() {
        propertyManager = new PropertyManager(getServletContext());
        fileManager = new FileManager(getServletContext());

        connector = new DatabaseConnector(
                propertyManager.getProperty("DatabaseUrl"),
                propertyManager.getProperty("DatabaseUser"),
                propertyManager.getProperty("DatabasePassword"),
                propertyManager.getProperty("DatabaseCurrent"));
        databaseController = new DatabaseController(connector,
                propertyManager.getProperty("DatabaseInitialize"));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String page = SendExecutor.execute(request, response, fileManager, databaseController);
        RenderExecutor.execute(page, request, databaseController, fileManager);

        page = "/" + page + ".jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
